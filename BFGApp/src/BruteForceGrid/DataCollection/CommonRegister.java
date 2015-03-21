package BruteForceGrid.DataCollection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;

/**
 * the component which contains the constant values and the vaues which
 * can be used to be edited by the user
 * 
 * @author Faraj
 */
public class CommonRegister
{
    public static String PEER_NAME = "MINI_HOST";
    public static int MAX_PARELLEL_CONNECTIONS = 5;
    public static String LOG_FILE = "log.txt";
    public static int DEFAULT_PORT = 800;
    public static int CONNCETIVITY_REPLIER_PORT(){return DEFAULT_PORT + 1;}
    public static int FILE_TRASFER_PORT(){return DEFAULT_PORT + 2;}
    public static int MAX_GRID_PEERS = 10;
    public static int MAX_REMOTE_PEERS_PER_JOB = 3;
    public static int MAX_REMOTE_JOBS = 10;
    public static int MAX_ERROR_COUNT_IP_LIST = 3;
    public static String REMOTE_IP_LIST_FILE_PATH = "ril.lst";
    public static String DECRYPTOR_LOCAL_ROOT_PATH = "decryptors/";
    public static int REMOTE_PEER_CONNECTOR_START_DELAY = 1000;
    public static int REMOTE_CONNECTIVITY_CHECK_DELAY = 1000;
    public static int COMMON_INTERFACE_START_DELAY = 1000;
    public static int REMOTE_PEER_CONNECTOR_TRYOUT_PERIOD = 5000;
    public static String WEB_SERVER_DOMAIN = "http://localhost/BFGServer";// "http://rajnox.itriss.com/BFGServer";
    public static String SERVER_IP_FILE =  "activeip.php";
    public static String PROBE_FILE =  "poke.php";
    public static String PLUGIN_CHKSUM = "checksum.php";
    public static String SERVER_IP_FILE_PATH(){return WEB_SERVER_DOMAIN + "/" + SERVER_IP_FILE;}
    public static String SERVER_IP_PROBE_URL(){return WEB_SERVER_DOMAIN + "/" + PROBE_FILE;}
    public static String SERVER_IP_PLUGIN_CHKSUM(){return WEB_SERVER_DOMAIN + "/" + PLUGIN_CHKSUM;}
    public static int LOCAL_SUB_JOB_SIZE = 100000;
    public static int TIME_TO_AWAIT_UNTIL_DOWNLOAD = 3; //sec
    public static int SIZE_OF_FILE_READBLE_AT_A_TIME = 1024;
    public static final int LINUX = 0;
    public static final int WINDOWS = 1;
    public static final int MAC = 2;
    public static int SHEDULER_POLL_TIME =5000;
    public static int PARELLEL_JOBS_PER_PEER=2;
    public static int MIN_CPU_USAGE = 5;
    public static String CONFIG_FILE = "bfgsettings.conf";
    public static int SUB_JOB_TIME_WAIT = 2;

    public static boolean initialize()
    {
        try
        {
            File f  = new File(CONFIG_FILE);
            if(!f.exists() || f.length() == 0)
            {
                f.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                writer.write("# ---- Default configuration file of BruteForceGrid ----");
                writer.newLine();
                writer.write("PEER_NAME " + PEER_NAME);
                writer.newLine();
                writer.write("MAX_PARELLEL_CONNECTIONS " + MAX_PARELLEL_CONNECTIONS);
                writer.newLine();
                writer.write("DEFAULT_PORT " + DEFAULT_PORT);
                writer.newLine();
                writer.write("MAX_GRID_PEERS " + MAX_GRID_PEERS);
                writer.newLine();
                writer.write("MAX_REMOTE_PEERS_PER_JOB " + MAX_REMOTE_PEERS_PER_JOB);
                writer.newLine();
                writer.write("MAX_REMOTE_SERVERS " + MAX_REMOTE_JOBS);
                writer.newLine();
                writer.write("MAX_ERROR_COUNT_IP_LIST " + MAX_ERROR_COUNT_IP_LIST);
                writer.newLine();
                writer.write("WEB_SERVER_DOMAIN " + WEB_SERVER_DOMAIN);
                writer.newLine();
                writer.write("LOCAL_SUB_JOB_SIZE " + LOCAL_SUB_JOB_SIZE);
                writer.newLine();
                writer.write("TIME_TO_AWAIT_UNTIL_DOWNLOAD " + TIME_TO_AWAIT_UNTIL_DOWNLOAD);
                writer.newLine();
                writer.write("SHEDULER_POLL_TIME " + SHEDULER_POLL_TIME);
                writer.newLine();
                writer.write("PARELLEL_JOBS_PER_PEER " + PARELLEL_JOBS_PER_PEER);
                writer.newLine();
                writer.write("SUB_JOB_TIME_WAIT " + SUB_JOB_TIME_WAIT);
                writer.newLine();
                writer.write("# --- end of configuration --- ");
                writer.flush();
            }

            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            while((line = reader.readLine())!=null)
            {
                String[] vArr = line.split(" ");

                if(line.startsWith("#", 0) || vArr.length != 2)
                {
                    continue;
                }
                
                if(vArr[0].equalsIgnoreCase("PEER_NAME"))
                {
                    PEER_NAME = vArr[1];
                }
                else if(vArr[0].equalsIgnoreCase("MAX_PARELLEL_CONNECTIONS"))
                {
                    MAX_PARELLEL_CONNECTIONS = Integer.valueOf(vArr[1]);
                }
                else if(vArr[0].equalsIgnoreCase("DEFAULT_PORT"))
                {
                    DEFAULT_PORT = Integer.valueOf(vArr[1]);
                }
                else if(vArr[0].equalsIgnoreCase("MAX_GRID_PEERS"))
                {
                    MAX_GRID_PEERS = Integer.valueOf(vArr[1]);
                }
                else if(vArr[0].equalsIgnoreCase("MAX_REMOTE_PEERS_PER_JOB"))
                {
                    MAX_REMOTE_PEERS_PER_JOB = Integer.valueOf(vArr[1]);
                }
                else if(vArr[0].equalsIgnoreCase("MAX_REMOTE_SERVERS"))
                {
                    MAX_REMOTE_JOBS = Integer.valueOf(vArr[1]);
                }
                else if(vArr[0].equalsIgnoreCase("MAX_ERROR_COUNT_IP_LIST"))
                {
                    MAX_ERROR_COUNT_IP_LIST = Integer.valueOf(vArr[1]);
                }
                else if(vArr[0].equalsIgnoreCase("WEB_SERVER_DOMAIN"))
                {
                    WEB_SERVER_DOMAIN = vArr[1];
                }
                else if(vArr[0].equalsIgnoreCase("LOCAL_SUB_JOB_SIZE"))
                {
                    LOCAL_SUB_JOB_SIZE = Integer.valueOf(vArr[1]);
                }
                else if(vArr[0].equalsIgnoreCase("TIME_TO_AWAIT_UNTIL_DOWNLOAD"))
                {
                    TIME_TO_AWAIT_UNTIL_DOWNLOAD = Integer.valueOf(vArr[1]);
                }
                else if(vArr[0].equalsIgnoreCase("SHEDULER_POLL_TIME"))
                {
                    SHEDULER_POLL_TIME = Integer.valueOf(vArr[1]);
                }
                else if(vArr[0].equalsIgnoreCase("PARELLEL_JOBS_PER_PEER"))
                {
                    PARELLEL_JOBS_PER_PEER = Integer.valueOf(vArr[1]);
                }
                else if(vArr[0].equalsIgnoreCase("SUB_JOB_TIME_WAIT"))
                {
                    SUB_JOB_TIME_WAIT = Integer.valueOf(vArr[1]);
                }
            }
            reader.close();
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
            ArrayList<String> data = new ArrayList<String>();

            File f = new File(CONFIG_FILE);

            if(!f.exists())
                f.createNewFile();

            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = "";
            while((line = reader.readLine()) != null)
            {
                String[] vArr = line.split(" ");

                if(line.startsWith("#", 0) || vArr.length != 2)
                {
                    data.add(line);
                    continue;
                }

                if(vArr[0].equalsIgnoreCase("PEER_NAME"))
                {
                    vArr[1] = PEER_NAME;
                }
                else if(vArr[0].equalsIgnoreCase("MAX_PARELLEL_CONNECTIONS"))
                {
                    vArr[1] = MAX_PARELLEL_CONNECTIONS+"";
                }
                else if(vArr[0].equalsIgnoreCase("DEFAULT_PORT"))
                {
                    vArr[1] = DEFAULT_PORT+"";
                }
                else if(vArr[0].equalsIgnoreCase("MAX_GRID_PEERS"))
                {
                    vArr[1] = MAX_GRID_PEERS+"";
                }
                else if(vArr[0].equalsIgnoreCase("MAX_REMOTE_PEERS_PER_JOB"))
                {
                    vArr[1] = MAX_REMOTE_PEERS_PER_JOB+"";
                }
                else if(vArr[0].equalsIgnoreCase("MAX_REMOTE_SERVERS"))
                {
                    vArr[1] = MAX_REMOTE_JOBS+"";
                }
                else if(vArr[0].equalsIgnoreCase("MAX_ERROR_COUNT_IP_LIST"))
                {
                   vArr[1] = MAX_ERROR_COUNT_IP_LIST+"";
                }
                else if(vArr[0].equalsIgnoreCase("WEB_SERVER_DOMAIN"))
                {
                    vArr[1] = WEB_SERVER_DOMAIN;
                }
                else if(vArr[0].equalsIgnoreCase("LOCAL_SUB_JOB_SIZE"))
                {
                    vArr[1] = LOCAL_SUB_JOB_SIZE+"";
                }
                else if(vArr[0].equalsIgnoreCase("TIME_TO_AWAIT_UNTIL_DOWNLOAD"))
                {
                    vArr[1] = TIME_TO_AWAIT_UNTIL_DOWNLOAD+"";
                }
                else if(vArr[0].equalsIgnoreCase("SHEDULER_POLL_TIME"))
                {
                    vArr[1] = SHEDULER_POLL_TIME+"";
                }
                else if(vArr[0].equalsIgnoreCase("PARELLEL_JOBS_PER_PEER"))
                {
                    vArr[1] = PARELLEL_JOBS_PER_PEER+"";;
                }
                else if(vArr[0].equalsIgnoreCase("SUB_JOB_TIME_WAIT"))
                {
                    vArr[1] = SUB_JOB_TIME_WAIT+"";
                }
                line = vArr[0] + " " + vArr[1];
                data.add(line);
            }
            reader.close();
            f.delete();
            f.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));

            for (int i = 0; i < data.size(); i++) {
                String string = data.get(i);
                writer.write(string);
                writer.newLine();
            }
            writer.flush();
            writer.close();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    /**
     * generate the decryptor path useing the  descrption and the OS
     * 
     * @param DESCR the Decrptor descrption
     * @param OS the Operating system number
     * @return the path of the decryptor
     * @see String
     */

    public static String GET_DECRYPTOR_PATH(String DESCR, int OS)
    {
        switch(OS)
        {
            case LINUX:
                return CommonRegister.DECRYPTOR_LOCAL_ROOT_PATH
                    +"/BFG"+DESCR;
            case WINDOWS:
                return CommonRegister.DECRYPTOR_LOCAL_ROOT_PATH
                    +"/BFG"+DESCR+".exe";
            case MAC:
                return CommonRegister.DECRYPTOR_LOCAL_ROOT_PATH
                    +"/BFG"+DESCR;
            default:
                return null;

        }
    }
    
    /**
     * use to get the descriptor of the decryptor using the path
     * 
     * @param path the descrptor path
     * @param OS the operating system number
     * @return the descrptor of the decryptor
     * @see String
     */
    public static String GET_DECRYPTOR_DISCRIPTION(String path, int OS)
    {
        switch(OS)
        {
            case LINUX:
                return path.substring(3, path.length());
            case WINDOWS:
                return path.substring(3, path.length()-4);
            case MAC:
                return path.substring(3, path.length());
            default:
                return null;
        }
    }
}
