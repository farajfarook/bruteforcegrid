package BruteForceGrid.DataCollection;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Faraj
 * 
 * this is the class to store the available decryptor plugins logical pointing
 * links
 */
public class AvailableDecryptorList
{
    public static ArrayList<Decryptor> decryptors;
    
    /**
     * <b>initialize the AvailableDecryptorList</b>
     * 
     * check the folder which contains the plugins and create the logical 
     * pointers
     */
    public static boolean Initalize()
    {
        decryptors = new ArrayList<Decryptor>();

        /**
         * to check the directory n get the available decryptors.
         */
       
         try
         {
            File dfile = new File(CommonRegister.DECRYPTOR_LOCAL_ROOT_PATH);

            if(!dfile.exists()||!dfile.isDirectory())
            {
                dfile.mkdir();
            }
            else if(!dfile.canExecute()||!dfile.canRead()||!dfile.canWrite())
            {
                throw new Exception("Permission denied");
            }

            /**
             * if files found the get the list
             */
            
            File[] files = dfile.listFiles();

            /**
             * and take the dicription of each decryptors
             */
            
             for (int i = 0; i < files.length; i++)
             {
                 File  file =  files[i];
                 if (file.canExecute()&&file.isFile())
                 {
                     String name = file.getName();
                     name = CommonRegister.GET_DECRYPTOR_DISCRIPTION(name,
                             PeerInfo.getOS());
                     decryptors.add(new Decryptor(name));
                 }
             }
            return true;

         }
         catch(Exception ex)
         {
                xError.Report(xError.FETCHING_LOCAL_DECRYPTORS,ex);
                return false;
         }
    }

    /** 
     * add new decryptor to the list
     * 
     * @param decryptorID to identyfy the decryptor to store the new decryptor pointer
     * @see Decryptor
     * @see String
     * 
     */
    
    public static void addDecryptor(String decryptorID)
    {
        decryptors.add(new Decryptor(decryptorID));
    }
    
    /**
     * remove the decrptor from the list
     * 
     * @param decryptorDiscription decryptor description of the decryptor
     * @return true/false of removed or not
     * @see String
     */
    
    public static boolean removeDecryptor(String decryptorDiscription)
    {
        for (int i = 0; i < decryptors.size(); i++)
        {
            Decryptor decryptor = decryptors.get(i);
            if(decryptor.getDiscription().equals(decryptorDiscription))
            {
                decryptors.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     * get the available decryptors count
     * 
     * @return the available decryptor count
     */
    public static int getDecryptorCount()
    {
        return decryptors.size();
    }

    /**
     * <b>shut down the decryptor List</b>
     * 
     * used when the process is to be terminated..
     * can be use to restore the allocated resouces
     */
    public static boolean shutdown()
    {
        try
        {
            decryptors.clear();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    /**
     * check wether the decryptor is available or not
     * 
     * @param decryptDiscr decryptor discription to identyfy the decryptors
     * @return the availability of the decryptor
     * @see String
     * @see Decryptor
     * 
     */
    public static boolean isAvailable(String decryptDiscr)
    {
        for (int i = 0; i < decryptors.size(); i++)
        {
            Decryptor decryptor = decryptors.get(i);
            if(decryptor.getDiscription().equals(decryptDiscr))
            {
                return true;
            }
        }
        return false;
    }
}
