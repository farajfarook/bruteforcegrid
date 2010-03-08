/**
 * This modules updates all the list and job components of the perticular peer
 *
 * if the peer fails the jobs and other list information is handled
 * by this component
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.*;

/**
 *
 * @author Faraj
 */
public class ListUpdater
{   
    public static void doUpdate(int gridPeerID)
    {
        RemoteJobList.deleteAllRemoteJobs(gridPeerID);
        LocalJob.resetLocalSubJob(gridPeerID);
    }
}
