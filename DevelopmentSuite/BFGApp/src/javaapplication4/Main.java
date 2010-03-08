/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication4;

import BruteForceGrid.WorkingComponent.*;
import GUI.Shower;

import javax.swing.UIManager;

/**
 *
 * @author Faraj
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
       UIManager.setLookAndFeel(new jtattoo.plaf.acryl.AcrylLookAndFeel());
       Initializer.doInit();
       Shower.runit();
    }
}
