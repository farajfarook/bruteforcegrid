package BruteForceGrid.DataCollection;
import BruteForceGrid.WorkingComponent.ListUpdater;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * the component which contains all the connected peer informations
 *
 * @author Faraj
 * @see GridPeer
 */
public class GridPeerList
{
   private static ArrayList<GridPeer> gridList;

   /**
    * the initialization process of the GridPeerList
    * initialization od grid Peer is also done inside
    */
   public static boolean  initialize()
   {
       try
       {
            gridList = new ArrayList<GridPeer>();
            GridPeer.initialize();
            return true;
       }
       catch(Exception e)
       {
           return false;
       }
   }

   /**
    * search for grid peer id using the address of connection
    * 
    * @param address the address of connection
    * @return the grid peer id of which the given address is connected or returns -999 on fail
    * @see InetAddress
    * @see GridPeer
    */
   public static int getGridPeerID(InetAddress address)
   {
       for (int i = 0; i < gridList.size(); i++) {
           GridPeer gridPeer = gridList.get(i);
           if(gridPeer.getConnectionSocket().getInetAddress().equals(address))
               gridPeer.getLocalPeerID();
       }
       return -999;
   }

   /**
    * search for gripeer using the index
    * 
    * @param index of the grid peer array
    * @return the gridpeer
    * @see GridPeer
    */
   public static GridPeer getGridPeerByIndex(int index)
   {
       return gridList.get(index);
   }

   /**
    * the function which insrt the entry of grid peer to the list
    * in this constructor the connecteion is automatically made
    * 
    * @param socket - the socket to which the connection is made
    * @return true/false of which the adding is done or not
    * @see Socket
    * @see GridPeers
    */
   public static boolean addGridPeer(Socket socket)
   {
       for (int i = 0; i < gridList.size(); i++)
       {
           GridPeer gridPeer = gridList.get(i);

           if(gridPeer.getAddress().equals(socket.getInetAddress()))
           {
                try {
                    gridPeer.setSocket(socket);
                } catch (Exception ex) {
                    return false;
                }
                return true;
           }
       }
        try {
            gridList.add((new GridPeer(socket.getInetAddress())).setSocket(socket));
        } catch (Exception ex) {
            return false;
        }
       return true;
   }

   /**
    * check the availability fo the grid peer using the remote address
    * 
    * @param ipaddress the remote address of which the test of going to be made
    * @return true/false of the availability
    * @see InetAddress
    */
   public static boolean checkavailability(InetAddress ipaddress)
   {       
      
       for (int i = 0; i < gridList.size(); i++) {
           GridPeer gridPeer = gridList.get(i);
           if(gridPeer.getAddress().getHostAddress().equals(ipaddress.getHostAddress()))
           {
               return true;
           }
       }
       return false;
   }
   
   /**
    * the function which insrt the entry of grid peer to the list
    * 
    * @param address the address to which the connection is going to be made
    * @return true/false of which the adding is done or not
    * @see InetAddress
    * @see GridPeers
    */
   public static boolean addGridPeer(InetAddress address)
   {
       if(checkavailability(address))
       {
           return true;
       }
        if(CommonRegister.MAX_GRID_PEERS > gridList.size())
        {
            GridPeer gp = null;
            try {
                new Socket(address, CommonRegister.CONNCETIVITY_REPLIER_PORT());
            } catch (IOException ex) {
                return false;
            }

            try
            {
                gp = new GridPeer(address);
            }
            catch (Exception ex)
            {
                xError.Notify(xError.CREATING_SOCKET_GRID_PEER);
                return false;
            }
            gridList.add(gp);
            return true;
        }
        else
        {
             xMessenger.miniMessege(xMessenger.PEER_LIST_FILLED);
             return false;
        }
   }

   /**
    * go thought the grid peer id and check for the
    * grid peer and return it
    * 
    * @param  gridPeerID the ID of which the grid peer is searched and returned
    * @see GridPeer
    */
   public static GridPeer getGridPeer(int gridPeerID) throws Exception
   {
       for (int i = 0; i < gridList.size(); i++)
       {
           GridPeer gp = gridList.get(i);
           if(gp.getLocalPeerID()==gridPeerID)
           {
               return gp;
           }
       }
       throw new Exception("no grid peer found");
   }

   /**
    * to get the availble grid peers count
    * 
    * @return the grid peers count
    */
   public static int getGridPeerCount()
   {
       return gridList.size();
   }

   /**
    *
    * @return connected grid peer count
    */
    public static int getConnectedGridPeerCount()
    {
       int cnt = 0;
       for (int i = 0; i < gridList.size(); i++) {
           GridPeer gridPeer = gridList.get(i);
           if(gridPeer.isConnected())
               cnt++;
       }
       return cnt;
    }

   /**
    * search the grid peer and remove it from the list
    * also diconnect the socket
    * 
    * @param gridPeerID the grid peer id of which the grid peer is to be rmoved
    * @see GridPeer
    */
   public static boolean removeGridPeer(int gridPeerID)
   {
       GridPeer gp = null;
        try {
            gp = getGridPeer(gridPeerID);
        } catch (Exception ex) {
            return false;
        }
       //disconnect the socket
       if(gp.removeGridPeer())
       {
           gridList.remove(gp);
           ListUpdater.doUpdate(gp.getLocalPeerID());
           return true;
       }
       else
       {
           return false;
       }
   }

   /**
    * remove the grid peer from the list using the inetAddess
    * 
    * @param address of which the grid peer is made of
    * @return true/false or removal
    * @see InetAddress
    */
   public static boolean removeGridPeer(InetAddress address)
   {
       for (int i = 0; i < gridList.size(); i++)
       {
           GridPeer gridPeer = gridList.get(i);
           if(gridPeer.getAddress()==address && gridPeer.removeGridPeer())
           {
                gridList.remove(i);
                ListUpdater.doUpdate(gridPeer.getLocalPeerID());
                return true;
           }
       }
       //disconnect the socket
       return false;
   }

   /**
    * connect thhe peers for the jobexchange
    */
   public static void connectPeers()
   {
       for (int i = 0; i < gridList.size(); i++) 
       {
           if(getConnectedGridPeerCount()<CommonRegister.MAX_REMOTE_PEERS_PER_JOB)
           {
               GridPeer gridPeer = gridList.get(i);
               if(!gridPeer.isConnected())
               {
                    try {
                        gridPeer.connect();
                    } catch (Exception ex) {
                        gridPeer.connected = false;
                    }
               }
           }
       }
   }
   
   /**
    * disconnec the peers after the local  job completion
    */
   public static void disConnectPeers()
   {
       for (int i = 0; i < gridList.size(); i++) 
       {
           GridPeer gridPeer = gridList.get(i);
           if(gridPeer.isConnected()&&
                   (!RemoteJobList.isHavingJobsFrom(gridPeer)))
           {
                try {
                    gridPeer.disconnect();
                } catch (Exception ex) {
                    gridPeer.connected = false;
                }
           }
       }
   }

   /**
    * shut down the grid peerList and dealocate the resources
    */
    public static boolean shutdown()
    {
        try
        {
            gridList.clear();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
        
    }

    /**
     * get the connected peer count
     * @return number of peers connected
     */
    public static int getConnectedPeerCount()
    {
        int count = 0;
        for (int i = 0; i < gridList.size(); i++) {
            GridPeer gridPeer = gridList.get(i);
            if(gridPeer.connected)
                count++;
        }
        return count;
    }

    /**
     * get the connectable peer count
     * @return number of peers connectable
     */
    public static int getConnectablePeerCount()
    {
        int count = 0;
        for (int i = 0; i < gridList.size(); i++) {
            GridPeer gridPeer = gridList.get(i);
            if(!gridPeer.connected)
                count++;
        }
        return count;
    }
}
