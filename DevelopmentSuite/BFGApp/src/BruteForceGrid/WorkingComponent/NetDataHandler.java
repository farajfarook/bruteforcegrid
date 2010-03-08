/**s
 * the component which handles the request and response from the remote peer
 * integrates all the handling work into a single place
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.AvailableDecryptorList;
import BruteForceGrid.DataCollection.GridPeer;
import BruteForceGrid.DataCollection.LocalJob;
import BruteForceGrid.DataCollection.LocalSubJob;
import BruteForceGrid.DataCollection.NetData;
import BruteForceGrid.DataCollection.PeerInfo;
import BruteForceGrid.DataCollection.RemoteIPList;
import BruteForceGrid.DataCollection.RemoteJobList;
import BruteForceGrid.DataCollection.RemoteSubJob;
import BruteForceGrid.DataCollection.SubJob;
import BruteForceGrid.DataCollection.xError;
import BruteForceGrid.DataCollection.xMessenger;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faraj
 */
public class NetDataHandler
{    
    public static void HandleResposeRemotePeerInfo(NetData data, GridPeer gridPeer)
    {
        // update the place that gona be updated..
    }

    public static void HandleResposePeerList(NetData data, GridPeer gridPeer)
    {
        int count = data.getParametersSize();
        for (int i = 0; i < count; i++)
        {
            String ip = data.getParameter(i);
            try {
                RemoteIPList.insertNewIP(InetAddress.getByName(ip));
            } catch (Exception ex) {
                xError.Notify("incorrect host ip :" + ip);
            }
        }
        xMessenger.miniMessege(xMessenger.NEW_IP_LIST_RECIEVED);
    }

    public static void HandleResponseStartJob(NetData data, GridPeer gridPeer)
    {
        /**
         * after gettong the cnofirmation from the remote peer
         * change the state of the localJob to the waiting state
        */
        boolean isAccepting = data.getParameter(1).equalsIgnoreCase(NetData.TRUE);

        if(isAccepting)
        {
            LocalJob.setState(gridPeer.getLocalPeerID(), LocalSubJob.IDLE);
        }
        else
        {
            LocalJob.setState(gridPeer.getLocalPeerID(), LocalSubJob.NEW);
        }
    }

    public static void HandleRequestRemotePeerInfo(NetData data, GridPeer gridPeer)
    {
        /**
         * response with the peer information
         */
        RemoteResponser.ResponseRemotePeerInfo(gridPeer,PeerInfo.doGet());
    }

    public static void HandleRequestStartJob(NetData data, GridPeer gridPeer)
    {
        /**
         * check for the peers capability of accepting remote jobs
         */
        if(BenchMark.isInNewJobAcceptanceCondition())
        {
            /**
             * crate a subjob from the data fetched from the socket
             */
            SubJob job = SubJob.generateSubJob(data.getParameter(1));

            /**
             * create a remote sub job using da data in subjob
             */
            RemoteSubJob remoteSubJob = new RemoteSubJob(job.getDecryptorDiscr(),
                    job.getJobID(), gridPeer.getLocalPeerID(), job.getSize(),job.getSegmentSize(),
                    job.getSegmentNum(), job.isCheckSimple(), job.isCheckCapital(), 
                    job.isCheckSpecial(), job.isCheckNumber(), job.getHash());

              /**
             * check for plugin availablity
             */
            boolean isAvailable =
                    AvailableDecryptorList.isAvailable(remoteSubJob.getDecryptorDiscr());

            if(!isAvailable)
            {
                if(DecryptorDownloader.Get(remoteSubJob.getDecryptorDiscr(),
                            gridPeer))
                {
                    AvailableDecryptorList.addDecryptor(remoteSubJob.getDecryptorDiscr());
                }
                else
                {
                    RemoteResponser.ResponseStartJob(gridPeer, false);
                    return;
                }
            }

            try {
                RemoteJobList.addRemoteJob(remoteSubJob);
                RemoteResponser.ResponseStartJob(gridPeer, true);
            } catch (Exception ex) {
                RemoteResponser.ResponseStartJob(gridPeer, false);
                return;
            }
        }
        else
        {
        /**
         * if the peer is busy then reject the request
         */
            RemoteResponser.ResponseStartJob(gridPeer, false);
            return;
        }
    }

    public static void HandleRequestToStop(NetData data, GridPeer gridPeer)
    {
        RemoteJobList.stopAllJob(gridPeer.getLocalPeerID());
        RemoteResponser.ResponseToStop(gridPeer,true);
    }

    public static void HandleRequestPeerList(NetData data, GridPeer gridPeer)
    {
        RemoteResponser.ResponsePeerList(gridPeer);
    }

    public static void HandleResponseToStop(NetData data, GridPeer gridPeer)
    {
        
    }

    public static boolean HandleRequestToPause(NetData data, GridPeer gridPeer)
    {
        SubJob job = SubJob.generateSubJob(data.getParameter(1));

        RemoteSubJob remoteSubJob = null;
        try {
            remoteSubJob = RemoteJobList.getRemoteJob(job.getGridPeerID(), job.getJobID());
        } catch (Exception ex) {
            return false;
        }

        remoteSubJob.setState(SubJob.PAUSED);
        return true;
    }

    public static void HandleResponseToPause(NetData data, GridPeer gridPeer)
    {
    
    }

    public static boolean  HandleRequestToResume(NetData data, GridPeer gridPeer)
    {
        SubJob job = SubJob.generateSubJob(data.getParameter(1));

        RemoteSubJob remoteSubJob = null;
        try {
            remoteSubJob = RemoteJobList.getRemoteJob(job.getGridPeerID(), job.getJobID());
        } catch (Exception ex) {
            return false;
        }

        remoteSubJob.setState(SubJob.IDLE);
        return true;
    }

    public static void HandleResponseToResume(NetData data, GridPeer gridPeer)
    {
    
    }

    public static void HandleRequestToUpdateJobStatus(NetData data, GridPeer gridPeer)
    {
        SubJob job = SubJob.generateSubJob(data.getParameter(1));
        boolean isUpdated = LocalJob.setState(gridPeer.getLocalPeerID(), job.getState());
        RemoteResponser.ResponseToUpdateJobStatus(gridPeer, isUpdated);
    }

    public static void HandleResponseToUpdateJobStatus(NetData data, GridPeer gridPeer)
    {

    }

    public static void HandleRequestToStopOnSuccess(NetData data, GridPeer gridPeer)
    {
        String password = data.getParameter(1);
        LocalJob.finishJob(password);
    }

    public static void HandleResponseToStopOnSuccess(NetData data, GridPeer gridPeer)
    {
        
    }
}
