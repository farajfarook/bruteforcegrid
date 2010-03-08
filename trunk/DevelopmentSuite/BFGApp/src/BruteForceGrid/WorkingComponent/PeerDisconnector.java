/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.GridPeerList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Faraj
 */
public class PeerDisconnector
{
    private static Timer timer;

    public static boolean initialize()
    {
        try
        {
         timer = new Timer();
         timer.schedule(new Disconnector(), 5000, 5000);
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
            timer.cancel();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }

    }
}


class Disconnector extends TimerTask
{

    @Override
    public void run()
    {
   //     GridPeerList.disConnectPeers();
    }

}