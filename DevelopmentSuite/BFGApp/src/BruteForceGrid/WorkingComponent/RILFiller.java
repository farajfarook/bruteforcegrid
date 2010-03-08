/**
 * the remote peer list that has to be tried out to connect is done
 * using the RIL list
 * this RIL list is updated by this component by fetching from the server
 * 
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.CommonRegister;
import BruteForceGrid.DataCollection.RemoteIPList;
import BruteForceGrid.DataCollection.xError;
import BruteForceGrid.DataCollection.xMessenger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Faraj
 * fetch the IPs from the web server annd fill out the IPs
 */
public class RILFiller extends TimerTask
{
    static BufferedReader reader = null;
    static URL url = null;
    static Timer timer;
    
    public static boolean initialize()
    {
        try
        {
            timer = new Timer();
            timer.schedule(new RILFiller(), 1000,10000);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    @Override
    public void run()
    {
        try
        {
            xMessenger.miniMessege("SERVER : " + CommonRegister.WEB_SERVER_DOMAIN);
            url = new URL(CommonRegister.SERVER_IP_FILE_PATH());
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
        }
        catch(Exception ex)
        {
            xError.Report(xError.CONNECTING_TO_SERVER_IP_FILE, ex);
            return;
        }
        try
        {
            if(reader.ready())
            {
                String string = reader.readLine();
                String[] data = string.split(" ");
                for (int i = 0; i < data.length; i++)
                {
                    String datum = data[i];
                    RemoteIPList.insertNewIP(InetAddress.getByName(datum));
                }

                RemoteIPList.backupIPList();
            }
        }
        catch (Exception ex)
        {
            xError.Report(xError.READING_SERVER_IP_LIST_FILE, ex);
        }
    }



    public static boolean shutdown()
    {
        timer.cancel();
        try {
            reader.close();
        } catch (Exception ex) {
            xError.Report(xError.CLOSING_REMOTE_IP_FILE, ex);
            return false;
        }
        return true;
    }

}
