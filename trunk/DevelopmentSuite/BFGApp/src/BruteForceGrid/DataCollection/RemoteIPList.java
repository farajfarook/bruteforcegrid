/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.DataCollection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 *
 * @author Faraj
 */
public class RemoteIPList
{
   public static ArrayList<IPDataSet> iPDataSets;
   private static int count;

   public static void insertNewIP(InetAddress address) throws Exception
   {
       removeIP(address);
       InetAddress ia = InetAddress.getLocalHost();
       InetAddress lp = InetAddress.getByName("127.0.0.1");
       if((!ia.equals(address)) && (!lp.equals(address)))
       {
           iPDataSets.add(new IPDataSet(address));
       }
   }

   public static IPDataSet[] getAllData()
   {
       IPDataSet[] data = new IPDataSet[iPDataSets.size()];
       for (int i = 0; i < iPDataSets.size(); i++) {
           IPDataSet iPDataSet = iPDataSets.get(i);
           data[i] = iPDataSet;
       }
       return data;
   }

   public static boolean removeIP(InetAddress address)
   {
       for (int i = 0; i < iPDataSets.size(); i++)
       {
           IPDataSet iPDataSet = iPDataSets.get(i);
           if(iPDataSet.address.equals(address))
           {
               iPDataSets.remove(iPDataSet);
               return true;
           }
       }
       return false;
   }

   public static IPDataSet getDataSet(int index) throws Exception
   {
       if(index<iPDataSets.size())
       {
           return iPDataSets.get(index);
       }
       else
       {
           throw new Exception("no dataset found");
       }
       
   }

   public static boolean backupIPList()
   {
       File file = new File(CommonRegister.REMOTE_IP_LIST_FILE_PATH);
       BufferedWriter writer = null;
       if(file.exists())
           file.delete();
        try
        {
            file.createNewFile();
        }
        catch (IOException ex)
        {
            xError.Report(xError.CREATING_IP_LIST_FILE, ex);
        }
        try
        {
            writer = new BufferedWriter(new FileWriter(file));
        }
        catch (IOException ex)
        {
            xError.Report(xError.OPENING_IP_LIST_FILE, ex);
        }

        for (int i = 0; i < iPDataSets.size(); i++)
        {
            IPDataSet iPDataSet = iPDataSets.get(i);
            if(iPDataSet.isRemovable())
                continue;
            try
            {
                writer.write(iPDataSet.address.getHostAddress());
                writer.newLine();
                writer.flush();
            }
            catch (IOException ex)
            {
                xError.Report(xError.WRITING_IP_LIST_FILE, ex);
            }
        }
        try
        {
            writer.close();
        }
        catch (IOException ex)
        {
            xError.Report(xError.CLOSING_IP_LIST_FILE, ex);
        }
        return true;
   }

   public static int getIPListCount()
   {
       return iPDataSets.size();
   }

   public static boolean initalize()
   {
        File file = new File(CommonRegister.REMOTE_IP_LIST_FILE_PATH);
        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException ex)
            {
                xError.Report(xError.CREATING_IP_LIST_FILE, ex);
                return false;
            }
        }

         iPDataSets = new ArrayList<IPDataSet>();
         count = 0;

        BufferedReader reader;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            while(reader.ready())
            {
                insertNewIP(InetAddress.getByName(reader.readLine()));
            }
        }
        catch (Exception ex) 
        {
            xError.Report(xError.OPENING_IP_LIST_FILE, ex);
            return false;
        }
        return true;
   }

    public static boolean shutdown()
    {
        try
        {
            backupIPList();
            iPDataSets.clear();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public static boolean setDisconnection(InetAddress address)
    {
        for (int i = 0; i < iPDataSets.size(); i++)
        {
            IPDataSet iPDataSet = iPDataSets.get(i);
            if(iPDataSet.address.equals(address))
            {
                iPDataSet.setDisconnect();
                return true;
            }
        }
        return false;
    }
}
