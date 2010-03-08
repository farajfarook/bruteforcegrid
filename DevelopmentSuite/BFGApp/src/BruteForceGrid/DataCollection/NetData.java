/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.DataCollection;

/**
 *
 * @author Faraj
 */
public class NetData
{
    public static final String DELEMETER = " "; // delemeter
    public static final String TRUE = "TRUE"; // true
    public static final String FALSE = "FALSE"; // false
    public static final int REQUEST_PEER_LIST = 0; //0
    public static final int RESPONSE_PEER_LIST = 1; //1 <ip> <ip>
    public static final int REQUEST_TO_STOP = 2; //2 <jobid>
    public static final int RESPONSE_TO_STOP = 3; //3 TRUE/FASE
    public static final int REQUEST_REMOTE_PEER_INFO = 4;//4
    public static final int RESPONSE_REMOTE_PEER_INFO = 5;//5 <peername>
    public static final int REQUEST_START_JOB = 6;//6 <localSubJob>
    public static final int RESPONSE_START_JOB = 7;//7 TRUE/FASE
    public static final int REQUEST_TO_PAUSE = 8; //8 <jobid>
    public static final int RESPONSE_TO_PAUSE = 9; //9 TRUE/FASE
    public static final int REQUEST_TO_RESUME = 10; //10 <jobid>
    public static final int RESPONSE_TO_RESUME = 11; //11 TRUE/FASE
    public static final int REQUEST_TO_UPDATE_JOB_STATUS = 12; //12 <remoteSubJob>
    public static final int RESPONSE_TO_UPDATE_JOB_STATUS = 13; //13 TRUE/FASE
    public static final int REQUEST_TO_STOP_ON_SUCCESS = 14; // 14 <output>
    public static final int RESPONSE_TO_STOP_ON_SUCCESS = 15; // 15 TRUE/FALSE

    int type;
    String[] parameters;
    String rawData;

    public NetData(String rawData)
    {
        this.rawData = rawData;
        String[] arr = rawData.split(DELEMETER);
        type = Integer.parseInt(arr[0]);
        parameters = arr;
    }

    public String getParameter(int index)
    {
        return parameters[index];
    }

    public int getType()
    {
        return type;
    }

    public int getParametersSize() 
    {
        return parameters.length - 1;
    }

    @Override
    public String toString() {
        return rawData;
    }

}
