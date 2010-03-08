/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package jtattoo.plaf.acryl;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;

import jtattoo.plaf.*;

public class AcrylToolBarUI extends AbstractToolBarUI {

    public static ComponentUI createUI(JComponent c) {
        return new AcrylToolBarUI();
    }

    public Border getRolloverBorder() {
        return AcrylBorders.getRolloverToolButtonBorder();
    }

    public Border getNonRolloverBorder() {
        return AcrylBorders.getToolButtonBorder();
    }

    public boolean isButtonOpaque() {
        return false;
    }

    public void paint(Graphics g, JComponent c) {
        int w = c.getWidth();
        int h = c.getHeight();
        JTattooUtilities.fillHorGradient(g, AcrylLookAndFeel.getTheme().getToolBarColors(), 0, 0, w, h - 2);
        if (toolBar.getOrientation() == JToolBar.HORIZONTAL && isToolBarUnderMenubar()) {
            g.setColor(Color.white);
            g.drawLine(0, 0, w, 0);
            g.drawLine(0, h - 2, w, h - 2);
            g.setColor(ColorHelper.darker(AcrylLookAndFeel.getToolbarColorDark(), 10));
            g.drawLine(0, 1, w, 1);
            g.drawLine(0, h - 1, w, h - 1);
        }
    }
}
