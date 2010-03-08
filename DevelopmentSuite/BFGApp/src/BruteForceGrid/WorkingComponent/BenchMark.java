/**
 * this component checks the peers conditon of workable stae
 * that is can perform a remorte job or not.
 *
 * the benchmark is done and the result is send back.
 */

package BruteForceGrid.WorkingComponent;

import BruteForceGrid.DataCollection.CommonRegister;
import BruteForceGrid.DataCollection.PeerInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 *
 * @author Faraj
 */
public class BenchMark
{
    public static boolean initialize()
    {
        return true;
    }

    public static boolean shutdown()
    {
        return true;
    }

    public static boolean isInNewJobAcceptanceCondition()
    {
        if(PeerInfo.getCPUusage() > CommonRegister.MIN_CPU_USAGE)
            return false;
        else
            return true;
    }
    
}
