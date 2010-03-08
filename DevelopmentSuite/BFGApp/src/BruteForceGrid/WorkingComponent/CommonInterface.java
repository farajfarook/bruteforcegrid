/**
 * the interface of the remote connections to initiate
 *
 * this is the listening server component which creates the remote connections
 * and add to the grid peer list
 * 
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TimerTask;
import java.util.Timer;

/**
 *
 * @author Faraj
 */

public class CommonInterface
{
    private static Timer timer = new Timer();
    private static Listener listener = new Listener();
    
    public static boolean  initialize()
    {
        try
        {
            timer.schedule(listener, CommonRegister.COMMON_INTERFACE_START_DELAY);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    public static boolean shutdown()
    {
        try
        {
            listener.shutit();
            timer.cancel();
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
}

class Listener extends TimerTask
{
    ServerSocket serverSocket;

    public Listener()
    {
        try
        {
            serverSocket = new ServerSocket(CommonRegister.DEFAULT_PORT);
        }
        catch(Exception ex)
        {
            xError.Report(xError.ERROR_STARTING_SERVER,ex);
        }
    }

    public void shutit()
    {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            xError.Report(xError.CLOSE_SERVER_SOCKET,ex);
        }
    }

    @Override
    public void run()
    {
        Socket remoteSocket = null;

        while(true)
        {
            if(serverSocket == null || serverSocket.isClosed())
            {
                break;
            }
            xMessenger.miniMessege(xMessenger.REMOTE_SERVICE_STARTED);
            try
            {
                remoteSocket = serverSocket.accept();
                xMessenger.miniMessege(xMessenger.REMOTE_PEER_CONNECTED);
            }
            catch (IOException ex)
            {
                xError.Notify(xError.CLIENT_CONNECTION_FAILED);
                continue;
            }
            
            GridPeerList.addGridPeer(remoteSocket);
            
        }
        cancel();
    }
}

