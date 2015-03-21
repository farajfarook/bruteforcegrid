/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BruteForceGrid.DataCollection;

import BruteForceGrid.WorkingComponent.ListUpdater;
import BruteForceGrid.WorkingComponent.RemoteRequestor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faraj
 */

public class RemoteSubJob extends SubJob
{
    public RemoteSubJob(String decryptorDiscr,
            int remoteJobID,
            int gridPeerID,
            int size,
            int SegSize,
            int Segnum,
            boolean checkSimple,
            boolean checkCapital,
            boolean checkSpecial, 
            boolean checkNumber,
            String hash
            )
    {
        super(decryptorDiscr, remoteJobID, gridPeerID, size,SegSize, Segnum, checkSimple,
                checkCapital, checkSpecial, checkNumber, hash, IDLE);
    }
}
