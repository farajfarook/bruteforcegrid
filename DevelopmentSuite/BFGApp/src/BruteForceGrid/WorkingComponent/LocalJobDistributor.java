/**
 * Thid component is resposible to distiribute the local job sets to the remote
 * peers for the execution.
 * 
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.CommonRegister;
import BruteForceGrid.DataCollection.GridPeer;
import BruteForceGrid.DataCollection.GridPeerList;
import BruteForceGrid.DataCollection.LocalJob;
import BruteForceGrid.DataCollection.LocalSubJob;
import BruteForceGrid.DataCollection.RemoteJobList;
import BruteForceGrid.DataCollection.RemoteSubJob;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Faraj
 */
public class LocalJobDistributor
{
    private static Timer timer, lTimer;

    public static boolean initialize()
    {
        try{
            timer = new Timer();
            lTimer = new Timer();
            return true;
        }
        catch(Exception e){
           return false;
        }
    }

    public static boolean shutdown()
    {
        try{
            timer.cancel();
            lTimer.cancel();
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    public static boolean startNew(String decryptorDescr, String hash,int size,
            boolean isSimple, boolean isCapital, boolean isSpecial, boolean isNumber)
    {
        try {
            LocalJob.startNewJob(decryptorDescr, hash, size, isSimple, isCapital,
                isSpecial, isNumber);
            LocalJob.generateRemoteSubJobs(CommonRegister.LOCAL_SUB_JOB_SIZE);
        } catch (Exception ex) {
            System.out.println(ex);
            LocalJob.clearall();
            return false;
        }
        timer.schedule(new LocalJobDistributionTask(), 1000, 100);
        lTimer.schedule(new LocalJobSelfDistributionTask(), 1000, 1);
        return true;
    }

}


class LocalJobSelfDistributionTask extends TimerTask
{
    private LocalSubJob localSubJob = null;
    @Override
    public void run() 
    {
        if(LocalJob.isFinished())
        {
            LocalJob.stopJob();
            this.cancel();
            return;
        }

        try {
            localSubJob = LocalJob.getUnassignedJob();
        } catch (Exception ex) {
            return;
        }

        if(!LocalJob.isLocalyAssigned())
        {

            try {
                
                RemoteJobList.addRemoteJob(
                        new RemoteSubJob(
                        localSubJob.getDecryptorDiscr(),
                        localSubJob.getJobID(),
                        0,
                        localSubJob.getSize(),
                        localSubJob.getSegmentSize(),
                        localSubJob.getSegmentNum(),
                        localSubJob.isCheckSimple(),
                        localSubJob.isCheckCapital(),
                        localSubJob.isCheckSpecial(),
                        localSubJob.isCheckNumber(),
                        localSubJob.getHash()));

               localSubJob.setGridPeerID(0);
               localSubJob.setState(LocalSubJob.IDLE);

            } catch (Exception ex) {
                return;
            }
        }
    }
}

class LocalJobDistributionTask extends TimerTask
{
    @Override
    public void run()
    {
        if(LocalJob.isFinished())
        {
            LocalJob.stopJob();
            this.cancel();
            return;
        }
        int localJobCount = LocalJob.getNewJobCount();
        int gridPeerCount = GridPeerList.getGridPeerCount();

        if(localJobCount>0&&gridPeerCount>0)
        {
            for (int i = 0; i < GridPeerList.getGridPeerCount(); i++)
            {
                GridPeer gp = GridPeerList.getGridPeerByIndex(i);
                if(gp.isBlocked())
                    continue;
                
                if((!gp.isConnected())||LocalJob.isAssigned(gp.getLocalPeerID()))
                {
                    continue;
                }
                LocalSubJob localSubJob;
                try
                {
                    localSubJob = LocalJob.getUnassignedJob();
                }
                catch (Exception ex)
                {
                    break;
                }
                
                localSubJob.setGridPeerID(gp.getLocalPeerID());
                localSubJob.setState(LocalSubJob.PENDING);
                RemoteRequestor.RequestToStartJob(gp, localSubJob);
            }
        }
    }
}
