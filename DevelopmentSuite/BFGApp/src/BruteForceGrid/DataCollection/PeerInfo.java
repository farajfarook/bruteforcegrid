/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.DataCollection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Date;

/**
 *
 * @author Faraj
 */

public class PeerInfo
{
    static String cmdTop = "top -n 2 -b -d 0.2";
    // returns user cpu usage

    public static String doGet()
    {

        return System.getProperty("os.name") + ":" +
                System.getProperty("os.arch") + ":" +
                System.getProperty("os.version");
    }

    public static int getOS()
    {
        if(System.getProperty("os.name").contains("Windows"))
            return CommonRegister.WINDOWS;
        else
            return CommonRegister.LINUX;
    }

    public static int getCPUusage()
    {
        if(getOS() == CommonRegister.LINUX)
            return (int)getLinuxCpu();
        else
            return (int)getWindowsCpu();
    }

    private static double getWindowsCpu()
    {
        ThreadMXBean TMB = ManagementFactory.getThreadMXBean();
        long time = new Date().getTime() * 1000000;
        long cput = 0;
        double cpuperc = 0;

        //Begin loop.

        if( TMB.isThreadCpuTimeSupported() )
        {
                if(new Date().getTime() * 1000000 - time > 1000000000) //Reset once per second
                {
                        time = new Date().getTime() * 1000000;
                        cput = TMB.getCurrentThreadCpuTime();
                }

                if(!TMB.isThreadCpuTimeEnabled())
                {
                        TMB.setThreadCpuTimeEnabled(true);
                }

                if(new Date().getTime() * 1000000 - time != 0)
                        cpuperc = (TMB.getCurrentThreadCpuTime() - cput) / (new Date().getTime() * 1000000.0 - time) * 100.0;
        }
        else
        {
            cpuperc = -1;
        }
        return cpuperc;
    }

    private static double getLinuxCpu()
    {
        double cpu = -1;
        try
        {
            // start up the command in child process
            String cmd = cmdTop;
            Process child = Runtime.getRuntime().exec(cmd);

            // hook up child process output to parent
            InputStream lsOut = child.getInputStream();
            InputStreamReader r = new InputStreamReader(lsOut);
            BufferedReader in = new BufferedReader(r);

            // read the child process' output
            String line;
            int emptyLines = 0;
            while(emptyLines<3)
            {
                line = in.readLine();
                if (line.length()<1) emptyLines++;
            }
            in.readLine();
            in.readLine();
            line = in.readLine();
            String delims = "%";
            String[] parts = line.split(delims);
            delims =" ";

            parts = parts[0].split(delims);
            cpu = Double.parseDouble(parts[parts.length-1]);
        }
        catch (Exception e)
        { // exception thrown
            return -1;
        }
        return cpu;
    }
}