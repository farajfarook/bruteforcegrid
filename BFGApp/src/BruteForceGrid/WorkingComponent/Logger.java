/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.CommonRegister;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Faraj
 */
public class Logger
{
    private static BufferedWriter writer;

    private static File f = new File(CommonRegister.LOG_FILE);

    public static boolean initialize()
    {
        try
        {        
            if(!f.exists())
            {
                f.createNewFile();
            }
        }
        catch(Exception ex)
        {
            return false;
        }
        return true;
    }
    
    public static void log(String val)
    {
        try {
            writer = new BufferedWriter(new FileWriter(f,true));
            writer.write(val);
            writer.newLine();
            writer.close();
        } catch (Exception ex) {

        }
    }

    public static boolean shutdown()
    {
        try
        {
            writer.close();
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }
}

