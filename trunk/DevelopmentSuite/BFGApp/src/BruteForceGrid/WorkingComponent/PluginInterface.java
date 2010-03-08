package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.xMessenger;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Faraj
 */
public class PluginInterface 
{
    public static boolean FLG_RUNNING, FLG_FINISHED, FLG_SUCCESS, FLG_ERROR;
    public static String PASSWORD;
    public static Process p = null;

    public static void kill()
    {
        if(p != null)
            p.destroy();
    }

    public static void RUN_PLUGIN(
            String path, 
            String hash, 
            int size,
            int segmentSize,
            int segmentNum,
            boolean isCapital, 
            boolean isSimple, 
            boolean isNumaric, 
            boolean isSpecial) throws Exception
    {
        FLG_RUNNING = true;
        
        try
        {
            String process = path + " " 
                    + ((isSpecial)?"1":"0") + " "
                    + ((isNumaric)?"1":"0") + " "
                    + ((isCapital)?"1":"0") + " "
                    + ((isSimple)?"1":"0") + " "
                    + size + " "
                    + segmentSize + " "
                    + segmentNum + " "
                    + hash;

            xMessenger.miniMessege(process);

            
            int line = 0;
            BufferedReader input = null;
            String str[] = null;

            p = Runtime.getRuntime().exec(process);
            input = new BufferedReader(
                new InputStreamReader(p.getInputStream()));

            str = input.readLine().split(" ");
            line = Integer.valueOf(str[0]);
            
            switch(line)
            {
                case -2:
                    FLG_ERROR = true;
                    FLG_SUCCESS = false;
                    break;
                case 0:
                    FLG_ERROR = false;
                    FLG_SUCCESS = false;
                    break;
                case 1:
                    FLG_SUCCESS = true;
                    FLG_ERROR = false;
                    PASSWORD = str[1];
                    break;
            }
            FLG_FINISHED = true;
            input.close();
        }
        catch (Exception err)
        {
            throw err;
        }
    }
}
