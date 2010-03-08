/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.GridPeer;
import BruteForceGrid.DataCollection.GridPeerList;
import BruteForceGrid.DataCollection.LocalSubJob;
import BruteForceGrid.DataCollection.NetData;
import BruteForceGrid.DataCollection.RemoteSubJob;
import BruteForceGrid.DataCollection.SubJob;

/**
 *
 * @author Faraj
 */
public class RemoteRequestor
{
    public static void RequestForIPList(GridPeer gridPeer)
    {
        NetData netData = new NetData(String.valueOf(NetData.REQUEST_PEER_LIST));
        gridPeer.send(netData);
    }

    public static void RequestForPeerInfo(GridPeer gridPeer)
    {
        NetData netData = 
                new NetData(String.valueOf(NetData.REQUEST_REMOTE_PEER_INFO));
        gridPeer.send(netData);
    }

    public static void RequestToStopJob(GridPeer gridPeer)
    {
        NetData netData = new NetData(String.valueOf(NetData.REQUEST_TO_STOP));
        gridPeer.send(netData);
    }

    public static void RequestToStartJob(GridPeer gridPeer, LocalSubJob localSubJob)
    {
        localSubJob.setState(LocalSubJob.PENDING);
        String data = String.valueOf(NetData.REQUEST_START_JOB);
        data += NetData.DELEMETER;
        data += localSubJob.toString();
        NetData netData = new NetData(data);
        gridPeer.send(netData);
    }

    public static boolean RequestToPauseJob(LocalSubJob localSubJob)
    {
        String rawData = String.valueOf(NetData.REQUEST_TO_PAUSE);
        rawData += NetData.DELEMETER;
        rawData += localSubJob;
        NetData data = new NetData(rawData);
        GridPeer gp = null;
        try {
            gp = GridPeerList.getGridPeer(localSubJob.getGridPeerID());
        } catch (Exception ex) {
            return false;
        }
        gp.send(data);
        return true;
    }

    public static boolean RequestToResumeJob(LocalSubJob localSubJob)
    {
        String rawData = String.valueOf(NetData.REQUEST_TO_RESUME);
        rawData += NetData.DELEMETER;
        rawData += localSubJob;
        NetData data = new NetData(rawData);
        GridPeer gp = null;
        try {
            gp = GridPeerList.getGridPeer(localSubJob.getGridPeerID());
        } catch (Exception ex) {
            return false;
        }
        gp.send(data);
        return true;
    }

    public static boolean RequestToUpdateJobStatus(RemoteSubJob job)
    {
        String rawData = String.valueOf(NetData.REQUEST_TO_UPDATE_JOB_STATUS);
        rawData += NetData.DELEMETER;
        rawData += job;
        NetData data = new NetData(rawData);
        GridPeer gp = null;
        try {
            gp = GridPeerList.getGridPeer(job.getGridPeerID());
        } catch (Exception ex) {
            ListUpdater.doUpdate(job.getGridPeerID());
            return false;
        }
        gp.send(data);
        return true;
    }

    public static void RequestToStopOnSuccess(RemoteSubJob job, String output)
    {
        String rawData = String.valueOf(NetData.REQUEST_TO_STOP_ON_SUCCESS);
        rawData += NetData.DELEMETER;
        rawData += output;

        NetData data = new NetData(rawData);
        GridPeer gp = null;

        try {
            gp = GridPeerList.getGridPeer(job.getGridPeerID());
        } catch (Exception ex) {
            ListUpdater.doUpdate(job.getGridPeerID());
            return;
        }
       
        gp.send(data);
    }
}
