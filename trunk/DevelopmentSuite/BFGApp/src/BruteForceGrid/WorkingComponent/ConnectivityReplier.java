/**
 * this commoponent is resposible for the remote peers to inform dat
 * the local peer is up or down..
 * 
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.CommonRegister;
import BruteForceGrid.DataCollection.xError;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Faraj
 */

public class ConnectivityReplier
{
    static ServerSocket serverSocket;
    static Timer timer;

    public static boolean initialize()
    {
        try
        {
            serverSocket = new ServerSocket(CommonRegister.CONNCETIVITY_REPLIER_PORT());
        }
        catch(Exception exception)
        {
            xError.Report(xError.START_CONNECTIVITY_REPLIER,exception);
            return false;
        }
        timer = new Timer();
        timer.schedule(new ConnectivityReplierRunner(), 1000);
        return true;
    }

    public static boolean  shutdown()
    {
        try
        {
            serverSocket.close();
            return true;
        }
        catch(Exception exception)
        {
            xError.Report(xError.STOP_CONNECTIVITY_REPLIER,exception);
            return false;
        }
    }
}


class ConnectivityReplierRunner extends TimerTask
{
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                ConnectivityReplier.serverSocket.accept();
            }
            catch (IOException ex)
            {
                continue;
            }
        }
    }
}