/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.DataCollection;

import java.util.ArrayList;

/**
 *
 * @author Faraj
 */
public class RemoteJobList
{
    private static ArrayList<RemoteSubJob> remoteJobs;
    
    public static boolean initialize()
    {
        try
        {
            remoteJobs = new ArrayList<RemoteSubJob>();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    
    public static boolean shutdown()
    {
        try
        {
            remoteJobs.clear();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public static RemoteSubJob getNextExecutableJob() throws Exception
    {
        for (int i = 0; i < remoteJobs.size(); i++) {
            RemoteSubJob remoteSubJob = remoteJobs.get(i);
            if(remoteSubJob.getState()==SubJob.IDLE)
                return remoteSubJob;
        }
        throw new Exception("remote subjob exceeded");
    }
    
    public static RemoteSubJob getRemoteJob(int GridPeerID, int jobID) throws Exception
    {
        for (int i = 0; i < remoteJobs.size(); i++) {
            RemoteSubJob remoteJob = remoteJobs.get(i);
            if(remoteJob.getJobID()==jobID && remoteJob.gridPeerID==GridPeerID)
                return remoteJob;
        }
        throw new Exception("no remote subjob");
    }

    public static void addRemoteJob(RemoteSubJob remoteJob) throws Exception
    {
        if(getRemoteJobCount() > CommonRegister.MAX_REMOTE_JOBS)
            throw new Exception("list exceeded");
        
        if(!isAvailable(remoteJob))
        {
            remoteJobs.add(remoteJob);
        }
    }

    public static boolean isAvailable(RemoteSubJob job)
    {
        for (int i = 0; i < remoteJobs.size(); i++) {
            RemoteSubJob remoteSubJob = remoteJobs.get(i);
            if(remoteSubJob.equals(job))
            {
                return true;
            }
        }
        return false;
    }

    public static RemoteSubJob[] getRemoteSubJobs()
    {
        RemoteSubJob[] jobs = new RemoteSubJob[remoteJobs.size()];
        for (int i = 0; i < jobs.length; i++) 
        {
            jobs[i] = remoteJobs.get(i);
        }
        return jobs;
    }

    public static void stopAllJob(int gridPeerID)
    {
        for (int i = 0; i < remoteJobs.size(); i++)
        {
            RemoteSubJob remoteJob = remoteJobs.get(i);
            if(remoteJob.getGridPeerID()==gridPeerID)
            {
                remoteJob.setState(RemoteSubJob.STOPED);
                remoteJobs.remove(remoteJob);
            }
        }
    }

    public static boolean deleteRemoteJob(RemoteSubJob job)
    {
        for (int i = 0; i < remoteJobs.size(); i++)
        {
            RemoteSubJob remoteJob = remoteJobs.get(i);
            if(remoteJob.equals(job))
            {
                remoteJobs.remove(i);
                return true;
            }
        }
        return false;
    }

    public static void deleteAllRemoteJobs(int gridPeerID)
    {
        for (int i = 0; i < remoteJobs.size(); i++)
        {
            RemoteSubJob remoteJob = remoteJobs.get(i);
            if(remoteJob.getGridPeerID()==gridPeerID)
            {
                remoteJobs.remove(remoteJob);
            }
        }
    }

    public static boolean isHavingJobsFrom(GridPeer gp)
    {
        for (int i = 0; i < remoteJobs.size(); i++) {
            RemoteSubJob remoteSubJob = remoteJobs.get(i);
            if(remoteSubJob.getGridPeerID() == gp.getLocalPeerID())
                return true;
        }
        return false;
    }

    public static int getRemoteJobCount()
    {
        return remoteJobs.size();
    }
}
