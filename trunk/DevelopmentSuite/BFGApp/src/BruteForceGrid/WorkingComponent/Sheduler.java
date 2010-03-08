package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.CommonRegister;
import BruteForceGrid.DataCollection.LocalJob;
import BruteForceGrid.DataCollection.PeerInfo;
import BruteForceGrid.DataCollection.RemoteJobList;
import BruteForceGrid.DataCollection.RemoteSubJob;
import BruteForceGrid.DataCollection.SubJob;
import BruteForceGrid.DataCollection.xError;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * module of sheduling the remote jobs
 * 
 * @author Faraj
 */

public class Sheduler 
{
    private static JobSheduler sheduler = null;  
    private static Timer timer = null;   
    
    /**
     * initialize the shedular. and start it
     */
            
    public static boolean initialize()
    {
        /**
         * continuesly run the process of job sheduling
         */
        try{
            timer = new Timer();
            sheduler = new JobSheduler();
            timer.schedule(sheduler,1000);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
        
    /**
     * shutdown the shedular
     */
    public static boolean shutdown()
    {
        try{
            timer.cancel();
            if(sheduler!=null){
                sheduler.cancel();
            }
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}

/**
 * class which is used to shedule a job
 * 
 * @author Faraj
 */
class JobSheduler extends TimerTask
{
    private RemoteSubJob currentSubJob = null;
   
    /**
     * the process of sheduling
     */
    @Override
    public void run() 
    {
        while(true)
        {
            /**
             * waits  untill the remote job list gets a job from a remote location
             * or else return the process
             */

            if(RemoteJobList.getRemoteJobCount()<=0)
            {
                try
                {
                    Thread.sleep(CommonRegister.SHEDULER_POLL_TIME);
                    continue;
                }
                catch(Exception ex)
                {
                    xError.Report(xError.SHEDULER_EXECUTION, ex);
                    continue;
                }
            }

           /**
             * gets the remote job for execution
             */
            try {
                currentSubJob = RemoteJobList.getNextExecutableJob();
            } catch (Exception ex) {
                try{
                    Thread.sleep(CommonRegister.SHEDULER_POLL_TIME);
                    continue;
                }
                catch(Exception e)
                {
                    xError.Report(xError.SHEDULER_EXECUTION, ex);
                    continue;
                }
            }

            /**
             * if distributed by the local peer then give the 
             * priority to execution
             */
            if(currentSubJob.getGridPeerID()==0)
            {
                currentSubJob.setState(SubJob.STARTED);
                 /**
                 * execute the plugin
                 */
                try
                {
                PluginInterface.RUN_PLUGIN(
                        CommonRegister.GET_DECRYPTOR_PATH(
                            currentSubJob.getDecryptorDiscr(),
                            PeerInfo.getOS()),
                        currentSubJob.getHash(),
                        currentSubJob.getSize(),
                        currentSubJob.getSegmentSize(),
                        currentSubJob.getSegmentNum(),
                        currentSubJob.isCheckCapital(),
                        currentSubJob.isCheckSimple(),
                        currentSubJob.isCheckNumber(),
                        currentSubJob.isCheckSpecial());
                }
                catch(Exception ex)
                {
                    currentSubJob.setState(-999);
                    RemoteRequestor.RequestToUpdateJobStatus(currentSubJob);
                    RemoteJobList.deleteRemoteJob(currentSubJob);
                }
                /**
                 * check the flags and set the final status fo the specific job
                 */

                if(PluginInterface.FLG_FINISHED)
                {
                    currentSubJob.setState(SubJob.FINISHED);
                    if(PluginInterface.FLG_SUCCESS)
                    {
                        currentSubJob.setState(SubJob.SUCCESS);
                        LocalJob.finishJob(PluginInterface.PASSWORD);
                    }
                }
                else
                {
                    currentSubJob.setState(SubJob.STOPED);
                }
                LocalJob.setState(0, currentSubJob.getState());
                RemoteJobList.deleteRemoteJob(currentSubJob);
                continue;
            }

            /**
             * change the staus to the --> STARTED
             */
            currentSubJob.setState(SubJob.STARTED);
            RemoteRequestor.RequestToUpdateJobStatus(currentSubJob);
            /**
             * execute the plugin
             */

                try
                {
                PluginInterface.RUN_PLUGIN(
                        CommonRegister.GET_DECRYPTOR_PATH(
                            currentSubJob.getDecryptorDiscr(),
                            PeerInfo.getOS()),
                        currentSubJob.getHash(),
                        currentSubJob.getSize(),
                        currentSubJob.getSegmentSize(),
                        currentSubJob.getSegmentNum(),
                        currentSubJob.isCheckCapital(),
                        currentSubJob.isCheckSimple(),
                        currentSubJob.isCheckNumber(),
                        currentSubJob.isCheckSpecial());
                }
                catch(Exception ex)
                {
                    currentSubJob.setState(-999);
                    RemoteRequestor.RequestToUpdateJobStatus(currentSubJob);
                    RemoteJobList.deleteRemoteJob(currentSubJob);
                }
            /**
             * check the flags and set the final status fo the specific job
             */

            if(PluginInterface.FLG_FINISHED)
            {
                currentSubJob.setState(SubJob.FINISHED);
                if(PluginInterface.FLG_SUCCESS)
                {
                    currentSubJob.setState(SubJob.SUCCESS);
                    if(currentSubJob.getGridPeerID() != 0)
                        RemoteRequestor.RequestToStopOnSuccess(currentSubJob,
                            PluginInterface.PASSWORD);
                    else
                        LocalJob.finishJob(PluginInterface.PASSWORD);
                }
            }
            else
            {
                currentSubJob.setState(SubJob.STOPED);   
            }
            RemoteRequestor.RequestToUpdateJobStatus(currentSubJob);
            RemoteJobList.deleteRemoteJob(currentSubJob);
        }
    }
}