package BruteForceGrid.DataCollection;

import java.net.InetAddress;

/**
 * the class which can contain the information about the connectable IPs
 * 
 * @author Faraj
 * @see InetAddress
 */
public class IPDataSet
{
    InetAddress address;
    int errorCount;
    boolean conncted, removable;

    /**
     * constructor contains the IPaddress
     * 
     * @param address of the connectable remote location
     */
    public IPDataSet(InetAddress address) 
    {
        this.address = address;
        errorCount = 0;
        conncted = false;
    }

    /**
     * 
     * @return printable way
     */
    @Override
    public String toString()
    {
        return address.getHostAddress()+" "+errorCount;
    }

    public String text()
    {
        return address.getHostAddress()+", tryouts : "+errorCount;
    }

    /**
     * get error count
     * @return
     */
    public int getErrorCount() {
        return errorCount;
    }

    /**
     * set status to connect
     */
    public void setConnect()
    {
        conncted = true;
        removable = false;
        errorCount = 0;
    }
    
    /**
     * set the statues to disconnct
     * 
     * @return done or not
     */
    public void setDisconnect()
    {
        conncted = false;
        removable = ++errorCount < CommonRegister.MAX_ERROR_COUNT_IP_LIST;
    }

    /**
     * to get the address
     * 
     * @return the Inetaddress of the IP
     */
    public InetAddress getAddress()
    {
        return address;
    }

    /**
     * to get the connectde statues
     * 
     * @return connectde staues
     */
    public boolean isConncted() {
        return conncted;
    }

    /**
     * is removable on next close
     * @return
     */
    public boolean isRemovable() {
        return removable;
    }


}
