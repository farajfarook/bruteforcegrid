/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.DataCollection;

import BruteForceGrid.WorkingComponent.RemoteRequestor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faraj
 */
public class SubJob
{
    private static String DELEMETER = ":";

    public static final int NEW = -2; // when the new job is created
    public static final int PENDING = -1; // when it is waiting to be set to a GP
    public static final int IDLE = 0; // in GP but not started yet
    public static final int STARTED = 2; // GP started
    public static final int STOPED = 3; // GP stoped n kill job
    public static final int FINISHED = 4; // GP finished execution
    public static final int SUCCESS = 5; // GP found the password
    public static final int PAUSED = 6; // GP paused

    String decryptorDiscr;
    int jobID;
    int gridPeerID;
    int size;
    int segmentSize;
    int segmentNum;
    boolean checkSimple,checkCapital,checkSpecial,checkNumber;
    int state;
    String hash;

    int timeWait = 0;

    public SubJob(String decryptorDiscr,
            int jobID,
            int gridPeerID,
            int size,
            int segmentSize,
            int segmentNum,
            boolean checkSimple,
            boolean checkCapital, 
            boolean checkSpecial,
            boolean checkNumber,
            String hash,
            int state)
    {
        this.decryptorDiscr = decryptorDiscr;
        this.jobID = jobID;
        this.gridPeerID = gridPeerID;
        this.size = size;
        this.segmentSize = segmentSize;
        this.segmentNum = segmentNum;
        this.checkSimple = checkSimple;
        this.checkCapital = checkCapital;
        this.checkSpecial = checkSpecial;
        this.checkNumber = checkNumber;
        this.hash = hash;
        this.state = state;
  
    }

    public boolean waitTime(int MAX_TIME)
    {
        timeWait++;
        if(timeWait > MAX_TIME)
        {
            timeWait = 0;
            return true;
        }
        return false;
    }
   
    @Override
    public String toString()
    {
        String s = "";
        s+=decryptorDiscr + SubJob.DELEMETER;
        s+=jobID + SubJob.DELEMETER;
        s+=gridPeerID + SubJob.DELEMETER;
        s+=size + SubJob.DELEMETER;
        s+=segmentSize + SubJob.DELEMETER;
        s+=segmentNum + SubJob.DELEMETER;
        s+=checkSimple + SubJob.DELEMETER;
        s+=checkCapital + SubJob.DELEMETER;
        s+=checkSpecial + SubJob.DELEMETER;
        s+=checkNumber + SubJob.DELEMETER;
        s+=hash + SubJob.DELEMETER;
        s+=state;
        return s;
    }

    public static SubJob generateSubJob(String val)
    {
        String[] sData = val.split(DELEMETER);
        return new SubJob(sData[0], //discriptor
                Integer.parseInt(sData[1]), //jobid
                Integer.parseInt(sData[2]), //gpid
                Integer.parseInt(sData[3]), //size
                Integer.parseInt(sData[4]), //size
                Integer.parseInt(sData[5]), //segnum
                Boolean.parseBoolean(sData[6]), //simple
                Boolean.parseBoolean(sData[7]), //capital
                Boolean.parseBoolean(sData[8]), //special
                Boolean.parseBoolean(sData[9]), //num
                sData[10], //hash
                Integer.parseInt(sData[11])); //state
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public void setGridPeerID(int gridPeerID)
    {
        this.gridPeerID = gridPeerID;
    }

    public int getState()
    {
        return state;
    }
    public String getHash()
    {
        return hash;
    }

    public int getJobID()
    {
        return jobID;
    }

    public int getSegmentSize()
    {
        return segmentSize;
    }


    public String getDecryptorDiscr()
    {
        return decryptorDiscr;
    }

    public int getGridPeerID()
    {
        return gridPeerID;
    }

    public boolean isCheckCapital() {
        return checkCapital;
    }

    public boolean isCheckNumber() {
        return checkNumber;
    }

    public boolean isCheckSimple() {
        return checkSimple;
    }

    public boolean isCheckSpecial() {
        return checkSpecial;
    }

    public int getSegmentNum() {
        return segmentNum;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object obj)
    {
        SubJob sj = (SubJob)obj;
        return ((this.jobID == sj.jobID)&&(this.gridPeerID == sj.gridPeerID));
    }

    public String display() throws Exception
    {
        String peer = "NOT ASSIGNED";
        if(gridPeerID>0)
        {
            try {
                GridPeer gp = GridPeerList.getGridPeer(gridPeerID);
                peer = gp.getAddress().getHostAddress();
            } catch (Exception ex) 
            {
      //          this.setState(FINISHED);
                peer = "UNDEFINED";
                throw new Exception();
            }
        }
        else if(gridPeerID == 0)
        {
            peer = "SELF ASSIGNED";
        }

        peer += ", ";
        peer += timeWait + ", ";

        String stateval = "";

        switch(state)
        {
            case FINISHED:
                stateval = "FINISHED";
                peer = "";
                break;
            case IDLE:
                stateval = "IDLE";
                break;
            case NEW:
                stateval = "NEW";
                break;
            case PAUSED:
                stateval = "PAUSED";
                break;
            case PENDING:
                stateval = "PENDING";
                break;
            case STARTED:
                stateval = "STARTED";
                break;
            case STOPED:
                stateval = "STOPPED";
                break;
            case SUCCESS:
                stateval = "SUCCESS";
                peer = "";
                break;
        }
        return "JOB [" + jobID + "] " + decryptorDiscr + ", SEG # : "
                + segmentNum +", "+ peer + stateval;
    }
}
