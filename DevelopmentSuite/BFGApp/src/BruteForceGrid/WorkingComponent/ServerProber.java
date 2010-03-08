/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.CommonRegister;
import BruteForceGrid.DataCollection.xError;
import BruteForceGrid.DataCollection.xMessenger;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faraj
 */
public class ServerProber
{
    public static Timer timer = new Timer();;
    public static boolean initialize()
    {
        try
        {
            timer.schedule(new Prober(),1000, 15000);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public static boolean shutdown()
    {
        try{
            timer.cancel();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}

class Prober extends TimerTask
{

    @Override
    public void run()
    {
        try
        {
            xMessenger.miniMessege(xMessenger.SERVER_PROBING);       
            URL url = new URL(CommonRegister.SERVER_IP_PROBE_URL());
            InputStream inputStream = url.openStream();
            inputStream.read();
            inputStream.close();
        }
        catch (Exception ex)
        {
            xError.Report(xError.CONNECTING_SERVER_TO_PROBE, ex);
            return;
        }
    }
}
