/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.CommonRegister;
import BruteForceGrid.DataCollection.LocalJob;
import BruteForceGrid.DataCollection.LocalSubJob;
import BruteForceGrid.DataCollection.SubJob;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faraj
 */
public class LocalJobMonitor
{
    private static Timer timer;
    public static boolean initialize()
    {
        timer = new Timer();
        timer.schedule(new MonitorLocalJob(), 5000, 5000);
        return true;
    }

    public static boolean shutdown()
    {
        timer.cancel();
        return true;
    }
}

class MonitorLocalJob extends TimerTask
{
    @Override
    public void run()
    {
        if(!LocalJob.isStarted())
        {
            return;
        }

        for (int i = 0; i < LocalJob.getNoOfSubJobs(); i++)
        {
            int state;
            SubJob job;
            try {
                job = LocalJob.getLocalSubJob(i);
            } catch (Exception ex) {
                continue;
            }

            state = job.getState();

            if(state==LocalSubJob.FINISHED
                    || state== LocalSubJob.NEW )
            {
                // no probs
            }
            else if(state == LocalSubJob.IDLE)
            {
                if(job.waitTime(CommonRegister.LOCAL_SUB_JOB_SIZE / 500))
                {
                    job.setState(SubJob.NEW);
                    job.setGridPeerID(-1);
                }
            }
            else
            {
                if(job.waitTime(CommonRegister.SUB_JOB_TIME_WAIT))
                {
                    job.setState(SubJob.NEW);
                    job.setGridPeerID(-1);
                }

            }
        }
    }
}