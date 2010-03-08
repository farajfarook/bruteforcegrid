package BruteForceGrid.DataCollection;

import BruteForceGrid.WorkingComponent.ListUpdater;
import BruteForceGrid.WorkingComponent.PluginInterface;
import BruteForceGrid.WorkingComponent.RemoteRequestor;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author Faraj
 */
public class LocalJob
{
    private static LocalSubJob[] localSubJobs = new LocalSubJob[0];
    private static String hash;
    private static boolean checkSimple,checkCapital,checkSpecial,checkNumber;
    private static int size;
    private static String decyptorDiscr;
    private static Timer timer;
    private static boolean started,paused,success;
    private static String password;
    private static int localCount = 0;
    
    public static boolean initialize()
    {
        started = false;
        password = null;
        paused = false;
        return true;
    }

    public static boolean shutdown()
    {
        return true;
    }

    public static void startNewJob(String decyptorDiscr, String hash, int size,
            boolean isSimple, boolean isCapital, boolean isSpecial, boolean isNumber)
    {
        success = false;
        LocalJob.hash = hash;
        LocalJob.size = size;
        LocalJob.decyptorDiscr = decyptorDiscr;
        LocalJob.checkCapital = isCapital;
        LocalJob.checkSimple = isSimple;
        LocalJob.checkNumber = isNumber;
        LocalJob.checkSpecial = isSpecial;
        LocalSubJob.count = 0;
        
        timer = new Timer();
        timer.schedule(new PeerConnector(),1000,1000);
        started = true;
        paused = false;
        
    }

    public static void finishJob(String password)
    {
        stopJob();
        LocalJob.password = password;
        success = true;
        started = false;
        paused = false;
    }

    public static void stopJob()
    {
        for (int i = 0; i < localSubJobs.length; i++) {
            LocalSubJob localSubJob = localSubJobs[i];
            localSubJob.setState(SubJob.STOPED);
            if(localSubJob.getState() == SubJob.IDLE ||
                    localSubJob.getState() == SubJob.PENDING )
            {
                GridPeer gp;
                try {
                    gp = GridPeerList.getGridPeer(localSubJob.getGridPeerID());
                    RemoteRequestor.RequestToStopJob(gp);
                } catch (Exception ex)
                {
                    ListUpdater.doUpdate(localSubJob.getGridPeerID());
                    continue;
                }
            }
        }
        started = false;
        paused = false;
        PluginInterface.kill();
        stop();
    }

    public static void clearall()
    {
        localSubJobs = new LocalSubJob[0];
        initialize();
    }

    public static void pauseJob()
    {
        for (int i = 0; i < localSubJobs.length; i++) {
            LocalSubJob localSubJob = localSubJobs[i];
            
            if(localSubJob.getState() != SubJob.FINISHED)
                localSubJob.setState(SubJob.PAUSED);
            
            if(localSubJob.getState() == SubJob.IDLE ||
                    localSubJob.getState() == SubJob.PENDING )
            {
                GridPeer gp;
                try {
                    gp = GridPeerList.getGridPeer(localSubJob.getGridPeerID());
                    RemoteRequestor.RequestToPauseJob(localSubJob);
                } catch (Exception ex)
                {
                    ListUpdater.doUpdate(localSubJob.getGridPeerID());
                    continue;
                }
            }
        }
        paused = true;
        success = false;
    }

    public static void resumeJob()
    {
        for (int i = 0; i < localSubJobs.length; i++) {
            LocalSubJob localSubJob = localSubJobs[i];

            if(localSubJob.getState() != SubJob.FINISHED)
                localSubJob.setState(SubJob.NEW);

            if(localSubJob.getState() == SubJob.IDLE ||
                    localSubJob.getState() == SubJob.PENDING )
            {
                GridPeer gp;
                try {
                    gp = GridPeerList.getGridPeer(localSubJob.getGridPeerID());
                    RemoteRequestor.RequestToResumeJob(localSubJob);
                } catch (Exception ex)
                {
                    ListUpdater.doUpdate(localSubJob.getGridPeerID());
                    continue;
                }
            }
        }
        paused = false;
    }

    public static boolean isFinished()
    {
        if(success)
            return true;

        for (int i = 0; i < localSubJobs.length; i++) 
        {
            LocalSubJob localSubJob = localSubJobs[i];
            if(localSubJob.getState()!= LocalSubJob.FINISHED)
                return false;
        }
        return true;
    }

    public static LocalSubJob getUnassignedJob() throws Exception
    {
        for (int i = 0; i < localSubJobs.length; i++) 
        {
            LocalSubJob localSubJob = localSubJobs[i];
            if(localSubJob.getState() == SubJob.NEW)
            {
                return localSubJob;
            }
        }
       
        throw new Exception("no LocalSubJob found");
    }

    private static void stop()
    {
        //stoping actiivities
        LocalSubJob.count = 0;
        localSubJobs = new LocalSubJob[0];
        timer.cancel();
        GridPeerList.disConnectPeers();
    }

    public static int getFinishJobCount()
    {
        int count = 0;
        for (int i = 0; i < localSubJobs.length; i++) 
        {
            LocalSubJob localSubJob = localSubJobs[i];
            if(localSubJob.getState()==LocalSubJob.FINISHED)
                count++;
        }
        return count;   
    }

    public static int getNewJobCount()
    {
        int count = 0;
        for (int i = 0; i < localSubJobs.length; i++) 
        {
            LocalSubJob localSubJob = localSubJobs[i];
            if(localSubJob.getState()==LocalSubJob.NEW)
                count++;
        }
        return count;
    }

    public static String getPassword() {
        return password;
    }

    public static boolean isLocalyAssigned()
    {

        for (int i = 0; i < localSubJobs.length; i++) {
            LocalSubJob localSubJob = localSubJobs[i];
            if((localSubJob.getGridPeerID()==0)
                    && (localSubJob.getState()!= SubJob.FINISHED))
            {
                 localCount++;
                 if(localCount >= 2)
                 {
                     localCount = 0;
                     return true;
                 }
            }
        }
        return false;
    }

    public static boolean isAssigned(int GridPeerID)
    {
        int jobCount = 0;
        for (int i = 0; i < localSubJobs.length; i++) {
            LocalSubJob localSubJob = localSubJobs[i];
            if((localSubJob.getGridPeerID()==GridPeerID)
                    && (localSubJob.getState()!= SubJob.FINISHED))
            {
                jobCount++;
                if(jobCount>=CommonRegister.PARELLEL_JOBS_PER_PEER)
                    return true;
            }
        }
        return false;
    }

    public static void resetLocalSubJob(int gridPeerID)
    {
        for (int i = 0; i < localSubJobs.length; i++) {
            LocalSubJob localSubJob = localSubJobs[i];
            if(localSubJob.getGridPeerID()==gridPeerID &&
                    (localSubJob.getState()!=SubJob.FINISHED ||
                        localSubJob.getState() != SubJob.SUCCESS))
            {
                localSubJob.setState(SubJob.NEW);
                localSubJob.setGridPeerID(-1);
                return;
            }
        }
    }

    public static LocalSubJob getLocalSubJobByID(int jobid) throws Exception
    {
        for (int i = 0; i < localSubJobs.length; i++) {
            LocalSubJob localSubJob = localSubJobs[i];
            if(localSubJob.getJobID() == jobid)
                return localSubJob;
        }
        throw new Exception("no LocalSubJob found");
    }

    public static boolean isStarted() {
        return started;
    }


    public static void generateRemoteSubJobs(int subJobSize) throws Exception
    {
        double count = 0;
        if(checkCapital)
            count+=26;
        if(checkSimple)
            count+=26;
        if(checkNumber)
            count+=10;
        if(checkSpecial)
            count+=32;
        int peercount = (int) Math.ceil(Math.pow(count, size)/subJobSize);

        if(peercount > 1000000)
            throw  new Exception("subjobs exceeded " + peercount);

        localSubJobs = new LocalSubJob[peercount];
        for (int i = 0; i < peercount; i++)
        {
            try 
            {
                localSubJobs[i] = 
                        new LocalSubJob(
                            decyptorDiscr,
                            size,
                            subJobSize,
                            i,
                            checkSimple,
                            checkCapital,
                            checkSpecial,
                            checkNumber,
                            hash);
            } 
            catch (Exception ex) 
            {
                xError.Report(xError.CREATING_LOCAL_SUB_JOBS,ex);
            }
      
        }
    }

    public static LocalSubJob getLocalUnfinishedSubJob(int val) throws Exception
    {
        for (int i = val; i < localSubJobs.length; i++) {
            LocalSubJob localSubJob = localSubJobs[i];
            if(localSubJob.state != SubJob.FINISHED)
                return localSubJob;
        }
        throw new Exception("no unfinished job found");
    }
    public static LocalSubJob getLocalSubJob(int index) throws Exception
    {
        LocalSubJob job = null;
        try
        {
            job = localSubJobs[index];
        }
        catch(Exception e)
        {
             throw new Exception("no LocalSubJob found");
        }
        return job;
    }

    public static boolean setState(int gridPeerID, int state)
    {
        for (int i = 0; i < localSubJobs.length; i++)
        {
            LocalSubJob localSubJob = localSubJobs[i];
            if(localSubJob.getGridPeerID()==gridPeerID)
            {
                localSubJob.setState(state);
                if(state == SubJob.NEW)
                {
                    localSubJob.setGridPeerID(-1);
                }
                return true;
            }
        }
        return false;
    }

    public static int getNoOfSubJobs()
    {
        return localSubJobs.length;
    }

    public static boolean isPaused() {
        return paused;
    }

    public static boolean isSuccess() {
        return success;
    }

    
}
class PeerConnector extends TimerTask
{
    @Override
    public void run()
    {
        GridPeerList.connectPeers();
    }

}