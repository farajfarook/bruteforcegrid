/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package jtattoo.plaf;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;

public class BaseButtonListener extends BasicButtonListener {

    public BaseButtonListener(AbstractButton b) {
        super(b);
    }

    public void focusGained(FocusEvent e) {
        AbstractButton b = (AbstractButton) e.getSource();
        b.repaint();
    }

    public void focusLost(FocusEvent e) {
        AbstractButton b = (AbstractButton) e.getSource();
        b.repaint();
    }

    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        AbstractButton button = (AbstractButton) e.getSource();
        button.getModel().setRollover(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        AbstractButton button = (AbstractButton) e.getSource();
        button.getModel().setRollover(false);
    }

    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }
}
