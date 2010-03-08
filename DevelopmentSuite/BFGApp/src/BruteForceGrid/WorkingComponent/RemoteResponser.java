/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.*;
/**
 *
 * @author Faraj
 */

public class RemoteResponser
{

    public static void ResponsePeerList(GridPeer sendGridPeer)
    {
        String rawData = String.valueOf(NetData.RESPONSE_PEER_LIST);

        for (int i = 0; i < GridPeerList.getGridPeerCount(); i++)
        {
            GridPeer gridPeer = GridPeerList.getGridPeerByIndex(i);
            rawData += NetData.DELEMETER;
            rawData += gridPeer.getAddress().getHostAddress();
        }

        NetData data = new NetData(rawData);
        xMessenger.miniMessege(xMessenger.PEER_LIST_SENDING,
                sendGridPeer.getAddress().getHostAddress());
        sendGridPeer.send(data);
    }

    public static void ResponseToStop(GridPeer gridPeer, boolean isStoped)
    {
        String rawData = String.valueOf(NetData.RESPONSE_START_JOB);
        rawData += NetData.DELEMETER;
        rawData += isStoped;
        NetData data = new NetData(rawData);
        gridPeer.send(data);
    }

    public static void ResponseStartJob(GridPeer gridPeer, boolean isAccepting)
    {
        String rawData = String.valueOf(NetData.RESPONSE_START_JOB);
        rawData += NetData.DELEMETER;
        rawData += isAccepting;
        NetData data = new NetData(rawData);
        gridPeer.send(data);
    }

    public static void ResponseRemotePeerInfo(GridPeer sendGridPeer, String info)
    {
        String rawData = String.valueOf(NetData.RESPONSE_REMOTE_PEER_INFO);
        rawData += NetData.DELEMETER;
        rawData += info;
        NetData data = new NetData(rawData);
        sendGridPeer.send(data);
    }

    public static void ResponseToPauseJob(GridPeer gridPeer, boolean isPaused)
    {
        String rawData = String.valueOf(NetData.RESPONSE_TO_PAUSE);
        rawData += NetData.DELEMETER;
        rawData += isPaused;
        NetData data = new NetData(rawData);
        gridPeer.send(data);
    }

    public static void ResponseToResumeJob(GridPeer gridPeer, boolean isResumed)
    {
        String rawData = String.valueOf(NetData.RESPONSE_TO_PAUSE);
        rawData += NetData.DELEMETER;
        rawData += isResumed;
        NetData data = new NetData(rawData);
        gridPeer.send(data);
    }

    public static void ResponseToUpdateJobStatus(GridPeer gridPeer, boolean isUpdated)
    {
        String rawData = String.valueOf(NetData.RESPONSE_TO_UPDATE_JOB_STATUS);
        rawData += NetData.DELEMETER;
        rawData += isUpdated;
        NetData data = new NetData(rawData);
        gridPeer.send(data);
    }
}
