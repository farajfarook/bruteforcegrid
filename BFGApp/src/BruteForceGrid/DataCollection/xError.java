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
public class xError
{
    public static String CREATING_IP_LIST_FILE = "error creating IP LIST FILE";
    public static String READING_IP_LIST_FILE = "cannot read IP LIST FILE";
    public static String WRITING_IP_LIST_FILE = "cannot write IP LIST FILE";
    public static String OPENING_IP_LIST_FILE = "cannot open IP LIST FILE";
    public static String CREATING_SOCKET_GRID_PEER = "error creating the socket in grid peer";
    public static String REMOVING_SOCKET_GRID_PEER = "error removeing peer in gridpeer";
    public static String REMOVING_IP_LIST_FILE = "error removeing ip list file";
    public static String CLOSING_IP_LIST_FILE = "error closing ip list file";
    public static String CONNECTING_TO_SERVER_IP_FILE = "error connecting to server's IP file";
    public static String READING_SERVER_IP_LIST_FILE = "cannot read IP LIST FILE in server";
    public static String CONNECTING_SERVER_TO_PROBE = "cannot probe to server";
    public static String CLOSING_REMOTE_IP_FILE = "cannot close ip file";
    public static String ERROR_STARTING_SERVER = "error starting server on port "+ CommonRegister.DEFAULT_PORT;
    public static String CLIENT_CONNECTION_FAILED = "remote client coonnection failed";
    public static String CLOSE_SERVER_SOCKET = "couldnt close local servce";
    public static String WRITE_TO_SOCKET = "unable to write data to remote peer";
    public static String READ_FROM_SOCKET = "unable to read data from remote peer";
    public static String REMOTE_STOP_REQUEST_CANCELED = "remote stop request canceled";
    public static String UNKNOWN_REMOTE_REQUEST = "remote request unknown";
    public static String PEER_CONECTION_LOST = "peer connecton lost";
    public static String UNKNOWN_REMOTE_NETDATA = "unknwn remote netdata";
    public static String START_CONNECTIVITY_REPLIER = "error creating connectivity replier";
    public static String STOP_CONNECTIVITY_REPLIER = "error stopping connectivity replier";
    public static String CONNECTING_DECRYPTOR_DOWNLOAD_POINT = "error connecting to decryptor download point";
    public static String CREATING_DECRYPTOR_LOCAL_FILE = "error creating decryptor local file";
    public static String CLOSING_DECRYPTOR_FILES_DOWNLOADED = "error closing decryptor file downloading";
    public static String FETCHING_DECRYPTOR_INFO = "error getting da decryptor information";
    public static String FILE_TRANSFER_SERVER = "file transfer server error";
    public static String FETCHING_LOCAL_DECRYPTORS = "error fetching local decryptors";
    public static String CREATING_LOCAL_SUB_JOBS = "local sub jobs creation error";
    public static String SHEDULER_EXECUTION = "Sheduler execution error";

    public static void Report(String string)
    {
   //     System.err.println(string);
        Shower.writeConsole(string);
        Logger.log(string);
    }
    public static void Report(String string, Exception ex)
    {
   //     System.err.println(string + " | " + ex.toString());
        Shower.writeConsole(string);
        Logger.log(string + " | " + ex);
    }
    public static void Notify(String string)
    {
   //     System.err.println(string);
        Shower.writeConsole(string);
        Logger.log(string);
    }
}

