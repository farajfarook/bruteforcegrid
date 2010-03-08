/**
 * the remote peer connectivity is monitored by this component
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Faraj
 */
public class RemoteConnectivityMonitor
{
    static Timer timer = new Timer();
    public static boolean  initialize()
    {
        try
        {
            timer.schedule(new Checker(), 1000, CommonRegister.REMOTE_CONNECTIVITY_CHECK_DELAY);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public static boolean shutdown()
    {
        try
        {
            timer.cancel();
            return true;
        }
        catch(Exception e){
            return false;
        }
        
    }
}

class Checker extends TimerTask
{

    @Override
    public void run()
    {
        
        for (int i = 0; i < GridPeerList.getGridPeerCount(); i++)
        {
            GridPeer gp = GridPeerList.getGridPeerByIndex(i);
            try
            {
                new Socket(gp.getAddress(),
                        CommonRegister.CONNCETIVITY_REPLIER_PORT());
            }
            catch(Exception e)
            {
                xError.Report(xError.PEER_CONECTION_LOST);
                RemoteIPList.setDisconnection(gp.getAddress());
                GridPeerList.removeGridPeer(gp.getLocalPeerID());
            }
        }
    }
}