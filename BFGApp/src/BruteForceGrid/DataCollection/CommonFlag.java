package BruteForceGrid.DataCollection;

import java.util.Timer;
import java.util.TimerTask;

/**
 * the class which contains the common flags which of in needed to 
 * the execution
 * 
 * @author Faraj
 * @see FlagUpdater
 */

public class CommonFlag
{
    
    public static boolean IP_LIST_EMPTY = true;
    public static boolean GRID_PEER_EMPTY = true;
    public static boolean GRID_PEER_FULL = false;

    private static Timer timer;
    
    /**
     * initialize the Common flag
     * 
     * @see FlagUpdater
     */
    public static void initialize()
    {
        timer = new Timer();
        timer.schedule(new FlagUpdater(), 1000, 2000);
    }
    /**
     * shut down the common flag
     */
    public static void shutdown()
    {
        timer.cancel();
    }
}

/**
 * the process which is going to update the flags automatically
 * 
 * @author Faraj
 * @see CommonFlag
 */
class FlagUpdater extends TimerTask
{
    /**
     * the function which is going to do the work of flag updation
     */
    @Override
    public void run()
    {
        CommonFlag.GRID_PEER_EMPTY =
                (GridPeerList.getGridPeerCount()==0);
        CommonFlag.GRID_PEER_FULL =
                (GridPeerList.getGridPeerCount()==CommonRegister.MAX_GRID_PEERS);
        CommonFlag.IP_LIST_EMPTY =
                (RemoteIPList.getIPListCount()==0);
    }
}