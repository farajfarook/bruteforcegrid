/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package jtattoo.plaf.acryl;

import javax.swing.*;
import javax.swing.plaf.*;

import jtattoo.plaf.*;

/**
 * @author Michael Hagen
 */
public class AcrylInternalFrameUI extends BaseInternalFrameUI {

    public AcrylInternalFrameUI(JInternalFrame b) {
        super(b);
    }

    public static ComponentUI createUI(JComponent c) {
        return new AcrylInternalFrameUI((JInternalFrame) c);
    }

    protected JComponent createNorthPane(JInternalFrame w) {
        titlePane = new AcrylInternalFrameTitlePane(w);
        return titlePane;
    }
}

