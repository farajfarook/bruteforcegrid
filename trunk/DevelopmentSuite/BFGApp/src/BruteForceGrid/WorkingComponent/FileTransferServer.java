/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.CommonRegister;
import BruteForceGrid.DataCollection.NetData;
import BruteForceGrid.DataCollection.PeerInfo;
import BruteForceGrid.DataCollection.xError;
import BruteForceGrid.DataCollection.xMessenger;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author faraj
 */
public class FileTransferServer
{
    static ServerSocket serverSocket;

    static FTServerServiceInitiator initiator;
    static Timer timer = new Timer();

    public static boolean initialize()
    {
        try {
            serverSocket = new ServerSocket(CommonRegister.FILE_TRASFER_PORT());
        } catch (IOException ex) {
            xError.Report(xError.FILE_TRANSFER_SERVER, ex);
            return false;
        }
        initiator = new FTServerServiceInitiator();
        timer.schedule(initiator, 1000);
        return true;
    }

    public static boolean  shutdown()
    {
        try
        {
            initiator.shutDown();
            initiator.cancel();
            timer.cancel();
            return  true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}


class FTServerServiceInitiator extends TimerTask
{
    private ArrayList<FTServerService> fTServerServices
            = new ArrayList<FTServerService>();
    private ArrayList<Timer> timers
            = new ArrayList<Timer>();

    @Override
    public void run()
    {
        while(true)
        {
            Socket s = null;
            try {
                s = FileTransferServer.serverSocket.accept();
            } catch (IOException ex) {
                 xError.Report(xError.FILE_TRANSFER_SERVER, ex);
                 continue;
            }
            FTServerService ftss = new FTServerService(s);
            Timer t = new Timer();
            t.schedule(ftss, 500);
            fTServerServices.add(ftss);
            timers.add(t);
        }
    }

    void shutDown()
    {
        for (int i = 0; i < fTServerServices.size(); i++)
        {
            FTServerService fTServerService = fTServerServices.get(i);
            Timer timer = timers.get(i);
            fTServerService.cancel();
            timer.cancel();
        }
    }
}

class FTServerService extends TimerTask
{

    private Socket socket;
    private File myFile;

    private boolean ready;

    public FTServerService(Socket socket)
    {
        this.socket = socket;
        ready = false;
        try
        {
            BufferedReader reader =
                    new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter writer =
                    new PrintWriter(socket.getOutputStream());
            
            xMessenger.miniMessege(xMessenger.FILE_REQUEST_RECIEVED);
            
            String path = CommonRegister.GET_DECRYPTOR_PATH(reader.readLine(),
                    PeerInfo.getOS());

            myFile = new File(path);
            
            if(myFile.exists())
            {
                writer.println(myFile.length() + NetData.DELEMETER + NetData.TRUE);
                writer.flush();
                xMessenger.miniMessege(xMessenger.FILE_FOUND_AND_ACK);
            }
            else
            {
                writer.println("0" + NetData.DELEMETER + NetData.FALSE);
                writer.flush();
                xMessenger.miniMessege(xMessenger.FILE_NOT_FOUND_AND_NACK);
                return;
            }
            
            xMessenger.miniMessege(xMessenger.WATING_FOR_CONFIRMATION);
            while(!reader.ready())
            {
                Thread.sleep(1000);
            }
            ready = true;
        }
        catch (Exception ex)
        {
            xError.Report(xError.FILE_TRANSFER_SERVER, ex);
            try {
                socket.close();
            } catch (IOException ex1) {
                xError.Report(xError.FILE_TRANSFER_SERVER, ex1);
            }
            this.cancel();
        }
    }

    @Override
    public void run()
    {
        if(!ready)
        {
            this.cancel();
            return;
        }
        
        byte [] mybytearray  = new byte [(int)myFile.length()];

        FileInputStream fis = null;

        try
        {
            fis = new FileInputStream(myFile);
        }
        catch (FileNotFoundException ex) 
        {
            xError.Report(xError.FILE_TRANSFER_SERVER, ex);
            try
            {
                socket.close();
            }
            catch (IOException ex1) 
            {
                xError.Report(xError.FILE_TRANSFER_SERVER, ex1);
            }
            this.cancel();
            return;
        }

        BufferedInputStream bis = new BufferedInputStream(fis);

        xMessenger.miniMessege(xMessenger.SERVER_FILE_TRANSFER_STARTED);
        try 
        {
             bis.read(mybytearray, 0, mybytearray.length);
             OutputStream os = socket.getOutputStream();
             os.write(mybytearray,0,mybytearray.length);
             os.flush();
             socket.close();         
             xMessenger.miniMessege(xMessenger.SERVER_FILE_TRANSFER_FINISHED);
        }
        catch (IOException ex) 
        {
            xError.Report(xError.FILE_TRANSFER_SERVER, ex);
            try 
            {
                socket.close();
            }
            catch (IOException ex1) 
            {
                xError.Report(xError.FILE_TRANSFER_SERVER, ex1);
            }
            this.cancel();
        }
    }
}