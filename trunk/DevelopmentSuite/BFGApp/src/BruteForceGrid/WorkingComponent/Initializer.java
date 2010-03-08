package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.*;
import GUI.Shower;

/**
 *
 * @author Faraj
 */
public class Initializer
{
    public static void doInit()
    {
        /**
         * data collection initiaization
         */
        message("CommonRegister",CommonRegister.initialize());
        message("Local Job",LocalJob.initialize());
        message("Remote IP List",RemoteIPList.initalize());
        message("Remote Job List",RemoteJobList.initialize());
        message("Available Decrptor List",AvailableDecryptorList.Initalize());
        message("Gridpeer List",GridPeerList.initialize());
     

        /**
         * working componet initialization
         */
        message("Logger",Logger.initialize());
        message("Connectivity Monitor",ConnectivityReplier.initialize());
        message("RIL Filler",RILFiller.initialize());
        message("Server Prober",ServerProber.initialize());
        message("Common Interface",CommonInterface.initialize());
        message("Remote Connectivity Monitor",RemoteConnectivityMonitor.initialize());
        message("Remote Peer Connector",RemotePeerTryer.initialize());
        message("Local Job Distributor",LocalJobDistributor.initialize());
        message("Benchmark",BenchMark.initialize());
        message("File Transfer Server",FileTransferServer.initialize());
        message("Sheduler",Sheduler.initialize());
        message("Disconnector",PeerDisconnector.initialize());
        message("LocalJobMonitor",LocalJobMonitor.initialize());
        

    }

    public static void message(String s, boolean success)
    {
        Shower.writeConsole("Starting " + s + "...");
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
