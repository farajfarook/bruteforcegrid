/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.*;
import java.util.*;



/**
 *
 * @author Faraj
 */
public class RemotePeerTryer
{
    static RemotePeerTryer remotePeerTryer;

    Timer timer;
    connectionSheduler sheduler;
    public static boolean initialize()
    {
        try
        {
            remotePeerTryer = new RemotePeerTryer();
            remotePeerTryer.sheduler = new connectionSheduler();
            remotePeerTryer.timer = new Timer();
            start();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public static void start()
    {
        remotePeerTryer.timer.schedule(remotePeerTryer.sheduler,
                CommonRegister.REMOTE_PEER_CONNECTOR_START_DELAY,
                CommonRegister.REMOTE_PEER_CONNECTOR_TRYOUT_PERIOD);
    }

    public static boolean shutdown()
    {
        try
        {
            remotePeerTryer.sheduler.shutall();
            remotePeerTryer.timer.cancel();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
class connectionSheduler extends TimerTask
{
    public int count = 0;
    Timer[] timers = new Timer[CommonRegister.MAX_PARELLEL_CONNECTIONS];


    public connectionSheduler()
    {
        for (int i = 0; i < timers.length; i++)
        {
            timers[i] = new Timer();
        }
    }

    @Override
    public void run() 
    {
        count=0;
        xMessenger.miniMessege(xMessenger.TRYING_OUT_NEW_PEERS);
        int gridpeeridcount = GridPeerList.getGridPeerCount();
        if(gridpeeridcount < CommonRegister.MAX_GRID_PEERS
                && RemoteIPList.getIPListCount() > count)
        {
            for (int i = 0; i < CommonRegister.MAX_PARELLEL_CONNECTIONS; i++)
            {
                IPDataSet iPDataSet = null;
                try {
                    iPDataSet = RemoteIPList.getDataSet(count++);
                } catch (Exception ex) {
                    return;
                }
                timers[i].schedule(new Connector(iPDataSet),100);
            }
        }
    }

    public void shutall()
    {
        for (int i = 0; i < timers.length; i++)
        {
            Timer timer = timers[i];
            timer.cancel();
        }
    }
}

class Connector extends TimerTask
{

    private IPDataSet iPDataSet;

    public Connector(IPDataSet dataSet)
    {
        iPDataSet = dataSet;
    }

    @Override
    public void run()
    {      
        if(iPDataSet==null || iPDataSet.isConncted())
        {
            this.cancel();
            return;
        }

        else if(!GridPeerList.checkavailability(iPDataSet.getAddress()))
        {
            xMessenger.miniMessege("adding : ("+iPDataSet.getErrorCount()
                    +"/"+CommonRegister.MAX_ERROR_COUNT_IP_LIST+")"
                    + iPDataSet.getAddress().getHostAddress());
            
                
            if(GridPeerList.addGridPeer(iPDataSet.getAddress()))
            {
                //if connected then set it and reset the error count
                iPDataSet.setConnect();
                xMessenger.miniMessege(iPDataSet.getAddress().getHostAddress()+" checked");
            }
            else if(iPDataSet != null)
            {
                //if not set to disconnect and increase the error count
                iPDataSet.setDisconnect();
            }
        }

        this.cancel();
    }
}