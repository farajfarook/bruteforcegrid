/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.*;
import GUI.Shower;

/**
 *
 * @author Faraj
 */
public class Shutdowner
{
    public static void doShut()
    {
         /**
         * data collection shutdown
         */

        message("Local Job",LocalJob.shutdown());
        message("Remote IP List",RemoteIPList.shutdown());
        message("Remote Job List",RemoteJobList.shutdown());
        message("Available Decrptor List",AvailableDecryptorList.shutdown());
        message("Gridpeer List",GridPeerList.shutdown());
        message("Common Register",CommonRegister.shutdown());

        /**
         * working componet shutdown
         */

        message("Connectivity Monitor",ConnectivityReplier.shutdown());
        message("RIL Filler",RILFiller.shutdown());
        message("Server Prober",ServerProber.shutdown());
        message("Common Interface",CommonInterface.shutdown());
        message("Remote Connectivity Monitor",RemoteConnectivityMonitor.shutdown());
        message("Remote Peer Connector",RemotePeerTryer.shutdown());
        message("Local Job Distributor",LocalJobDistributor.shutdown());
        message("Benchmark",BenchMark.shutdown());
        message("File Transfer Server",FileTransferServer.shutdown());
        message("Sheduler",Sheduler.shutdown());
        message("Disconnector", PeerDisconnector.shutdown());
        message("Logger", Logger.shutdown());
        message("LocalJobMonitor", LocalJobMonitor.shutdown());
        message("Config store", CommonRegister.shutdown());
    }

 public static void message(String s, boolean success)
    {
        Shower.writeConsole("Stoping " + s + "...");
        //System.out.print("Starting " + s + "...");
        if(success)
        {
          //  System.out.println("[OKEY]");
            Shower.writeConsole("[OKEY]");
        }
        else
        {
            Shower.writeConsole("[FAILED]");
            //System.out.println("[FAILED]");
        }
    }
}
