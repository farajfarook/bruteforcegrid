/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.DataCollection;

import BruteForceGrid.WorkingComponent.Logger;
import GUI.Shower;

/**
 *
 * @author Faraj
 */
public class xMessenger
{
    public static String PEER_LIST_FILLED = "Peer list Filled";
    public static String REMOTE_SERVICE_STARTED = "remote service started";
    public static String REMOTE_PEER_CONNECTED = "remote peer connected";
    public static String SERVER_PROBING = "server probeing now...";
    public static String REMOTE_NETDATA_RECIEVED = "remote request recieved";
    public static String PEER_LIST_SENDING = "sending peer list to remote location";
    public static String NEW_IP_LIST_RECIEVED = "new ip list recived and updated!";
    public static String TRYING_OUT_NEW_PEERS = "trying out new peers...";
    public static String WAITING_FOR_FILE_TRASFER = "waiting for file transfer";
    public static String FILE_TRASFER_STARTED = "transfer started";
    public static String FILE_TRASFER_COMPLETED = "transfer completed";
    public static String SENDING_FILE_REQUEST = "sending file server the request to get da file";
    public static String WAITING_FORFILE_RESPONSE = "wating for file server to response";
    public static String ACK_FROM_FILE_SERVER = "file found response recived by server";
    public static String NACK_FROM_FILE_SERVER = "file not found response by server";
    public static String SERVER_FILE_TRANSFER_FINISHED = "server finished trasmiting the file";
    public static String SERVER_FILE_TRANSFER_STARTED = "server started trasmitting the file";
    public static String WATING_FOR_CONFIRMATION = "server waiting for file confirmation";
    public static String FILE_NOT_FOUND_AND_NACK = "requested file not found";
    public static String FILE_FOUND_AND_ACK = "requested file found";
    public static String FILE_REQUEST_RECIEVED = "client request recived";

    public static void miniMessege(String string, String string2)
    {
      //  System.out.println(string + string2);
        Shower.writeConsole(string + string2);
        Logger.log(string + string2);
    }
    public static void miniMessege(String string)
    {
     //   System.out.println(string);
        Shower.writeConsole(string);
        Logger.log(string);
    }
}
