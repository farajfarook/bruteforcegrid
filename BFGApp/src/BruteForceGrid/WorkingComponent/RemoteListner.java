/**
 * The listener which is listing to the remote peer requests
 * this is a logical interface of all the peers connted
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.*;
import java.util.TimerTask;

/**
 *
 * @author Faraj
 */
public class RemoteListner extends TimerTask
{

    GridPeer gridPeer = null;

    public RemoteListner(GridPeer gridPeerByIndex)
    {
        this.gridPeer = gridPeerByIndex;
    }

    @Override
    public void run()
    {
        NetData netData = null;
        try
        {
            netData = gridPeer.recieve();
        } 
        catch (Exception ex)
        {
            GridPeerList.removeGridPeer(gridPeer.getLocalPeerID());
            cancel();
            return;
        }

        if(netData!=null)
        {
            xMessenger.miniMessege(xMessenger.REMOTE_NETDATA_RECIEVED);
            switch(netData.getType())
            {
                case NetData.REQUEST_TO_STOP :
                    NetDataHandler.HandleRequestToStop(netData, gridPeer);
                    break;

                case NetData.REQUEST_PEER_LIST :
                    NetDataHandler.HandleRequestPeerList(netData, gridPeer);
                    break;

                case NetData.REQUEST_START_JOB :
                    NetDataHandler.HandleRequestStartJob(netData, gridPeer);
                    break;

                case NetData.REQUEST_REMOTE_PEER_INFO:
                    NetDataHandler.HandleRequestRemotePeerInfo(netData, gridPeer);
                    break;

                case NetData.RESPONSE_PEER_LIST :
                    NetDataHandler.HandleResposePeerList(netData,gridPeer);
                    break;

                case NetData.RESPONSE_REMOTE_PEER_INFO :
                    NetDataHandler.HandleResposeRemotePeerInfo(netData, gridPeer);
                    break;

                case NetData.RESPONSE_START_JOB:
                    NetDataHandler.HandleResponseStartJob(netData, gridPeer);
                    break;

                case NetData.RESPONSE_TO_STOP:
                    NetDataHandler.HandleResponseToStop(netData, gridPeer);
                    break;

                case NetData.RESPONSE_TO_PAUSE:
                    NetDataHandler.HandleResponseToPause(netData, gridPeer);
                    break;

                case NetData.RESPONSE_TO_RESUME:
                    NetDataHandler.HandleResponseToResume(netData, gridPeer);
                    break;

                case NetData.REQUEST_TO_PAUSE:
                    NetDataHandler.HandleRequestToPause(netData, gridPeer);
                    break;

                case NetData.REQUEST_TO_RESUME:
                    NetDataHandler.HandleRequestToResume(netData, gridPeer);
                    break;

                case NetData.REQUEST_TO_UPDATE_JOB_STATUS:
                    NetDataHandler.HandleRequestToUpdateJobStatus(netData, gridPeer);
                    break;

                case NetData.RESPONSE_TO_UPDATE_JOB_STATUS:
                    NetDataHandler.HandleResponseToUpdateJobStatus(netData, gridPeer);
                    break;

                case NetData.REQUEST_TO_STOP_ON_SUCCESS:
                    NetDataHandler.HandleRequestToStopOnSuccess(netData, gridPeer);
                    break;

                case NetData.RESPONSE_TO_STOP_ON_SUCCESS:
                    NetDataHandler.HandleResponseToStopOnSuccess(netData, gridPeer);
                    break;

                default:
                    xError.Report(xError.UNKNOWN_REMOTE_NETDATA);
                    break;
            }
        }
    }
}

