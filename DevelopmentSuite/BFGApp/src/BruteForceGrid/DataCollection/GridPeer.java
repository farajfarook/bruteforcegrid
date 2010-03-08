package BruteForceGrid.DataCollection;

import BruteForceGrid.WorkingComponent.RemoteListner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Timer;

/**
 * the entry component class which contains the information 
 * of the connected peers
 * 
 * @author Faraj
 */
public class GridPeer
{
    private static int localPeerIDCount = 0;
    Socket connectionSocket;
    InetAddress address;
    BufferedReader reader;
    PrintWriter writer;
    int localPeerID;
    Timer listenerTimer;
    boolean connected;
    boolean blocked;
    /**
     * initialize the overall gridpeer entry component
     */
    public static void initialize()
    {
        localPeerIDCount = 0;

    }

    /**
     * peer blocking util
     * @return
     */
    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * constructor of the gripeer. 
     * this function does not creates the connection..
     * 
     * @param inetAddress address of the remote location to which the connection made
     * @throws java.lang.Exception
     * @see InetAddress
     */
    public GridPeer(InetAddress inetAddress)
    {
        connected = false;
        blocked = false;
        this.address = inetAddress;
    }

    /**
     * function which is connected the Gridpeer with the remote peer
     * @throws Exception
     */
    public void connect() throws Exception
    {
        if(connected)
            return;
        connectionSocket = new Socket(address, CommonRegister.DEFAULT_PORT);
        localPeerID = ++localPeerIDCount;
        initListener();
        reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        writer = new PrintWriter(connectionSocket.getOutputStream(),true);
        connected = true;
    }

    /**
     * the function which disconnect the connection with the remote peer
     *
     * @throws Exception
     */
    public void disconnect() throws Exception
    {
        if(!connected)
            return;
        connectionSocket.close();
        reader.close();
        writer.close();
        listenerTimer.cancel();
        connected = false;

    }

    /**
     * start the listener for the specific grid peer for any data flow inside
     * this calls the listing component of remoteLister
     * 
     * @see RemoteListner
     */
    private void initListener()
    {
        listenerTimer = new Timer();
        listenerTimer.schedule(new RemoteListner(this), 1000, 2000);
    }

    /**
     * constructor creating the gridpeer with the already connected socket
     * 
     * @param socket the socket of whcih the grid peer is created
     * @throws java.lang.Exception
     * @see Socket
     */
    public GridPeer setSocket(Socket socket) throws Exception
    {
        connected = true;
        connectionSocket = socket;
        address = connectionSocket.getInetAddress();
        localPeerID = ++localPeerIDCount;
        reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        writer = new PrintWriter(connectionSocket.getOutputStream(),true);
        initListener();
        return this;
    }


    /**
     * get the address of the remote location of the Grid peer
     * 
     * @return the address of the grid peer
     * @see InetAddress
     */
    public InetAddress getAddress()
    {
        return address;
    }

    /**
     * function used to distermin the grid peer has been conneted or not
     * @return is connected
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * close the listener for the gridpeer
     * 
     * @return close state of the grid peer listner
     * 
     */
    public boolean removeGridPeer()
    {
        if(!connected)
            return true;
        try
        {
            listenerTimer.cancel();
            this.connectionSocket.close();
            return true;
        } 
        catch (IOException ex) 
        {
            xError.Report(xError.REMOVING_SOCKET_GRID_PEER,ex);
            return  false;
        }
    }

    /**
     * 
     * send data flow to the remote grid peer
     * 
     * @param data the netdata format of information that is to be sent
     * @return true or false of data written
     * @see NetData
     */
    public boolean send(NetData data)
    {
        writer.println(data.toString());
        xMessenger.miniMessege(">>>"+ data.toString());
        return true;
    }

    /**
     * read the recived information in netdata format
     * 
     * @return the netdata object of the read data
     * @throws java.lang.Exception
     * @see NetData
     */
    public NetData recieve() throws Exception
    {
        NetData nd;


        try {
            if(reader.ready())
            {
                nd = new NetData(reader.readLine());
            }
            else
            {
                return null;
            }
        }
        catch (IOException ex)
        {
            xError.Report(xError.READ_FROM_SOCKET);
            throw new Exception();
        }
        xMessenger.miniMessege("<<<"+ nd.toString());
        return nd;
    }

    /**
     * to get the grid peer identification
     * 
     * @return the local grid peerID
     */
    public int getLocalPeerID()
    {
        return localPeerID;
    }

    /**
     * to get the connection socket fo the grid peer
     * 
     * @return the socket of which the connection of the grd peer is made of
     */
    public Socket getConnectionSocket()
    {
        return connectionSocket;
    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString()
    {
        if(connected)
            return "+" + address.getHostAddress();
        else
            return "-" + address.getHostAddress();
    }


}
