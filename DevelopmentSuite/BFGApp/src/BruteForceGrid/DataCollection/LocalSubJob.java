/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.DataCollection;

/**
 *
 * @author Faraj
 */
public class LocalSubJob extends SubJob
{
    public static int count = 0;

    public LocalSubJob(String decryptorDiscr,
            int size,
            int segmentSize,
            int segmentNum, 
            boolean checkSimple,
            boolean checkCapital,
            boolean checkSpecial, 
            boolean checkNumber,
            String hash) throws Exception
    {
        super(decryptorDiscr, count++, -1, size,segmentSize, segmentNum,checkSimple,
                checkCapital, checkSpecial, checkNumber, hash, NEW);
    }

    @Override
    public void setState(int state) {
        super.setState(state);
        if(this.state==FINISHED || this.state==SUCCESS)
        {
            this.gridPeerID = -10;
        }
    }


}
