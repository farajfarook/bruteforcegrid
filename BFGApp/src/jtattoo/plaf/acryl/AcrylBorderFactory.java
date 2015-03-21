/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package jtattoo.plaf.acryl;

import javax.swing.border.*;

import jtattoo.plaf.*;

/**
 * @author Michael Hagen
 */
public class AcrylBorderFactory implements AbstractBorderFactory {

    private static AcrylBorderFactory instance = null;

    private AcrylBorderFactory() {
    }

    public static synchronized AcrylBorderFactory getInstance() {
        if (instance == null) {
            instance = new AcrylBorderFactory();
        }
        return instance;
    }

    public Border getButtonBorder() {
        return AcrylBorders.getButtonBorder();
    }

    public Border getToggleButtonBorder() {
        return AcrylBorders.getToggleButtonBorder();
    }

    public Border getTextBorder() {
        return AcrylBorders.getTextBorder();
    }

    public Border getSpinnerBorder() {
        return AcrylBorders.getSpinnerBorder();
    }

    public Border getTextFieldBorder() {
        return AcrylBorders.getTextFieldBorder();
    }

    public Border getComboBoxBorder() {
        return AcrylBorders.getComboBoxBorder();
    }

    public Border getTableHeaderBorder() {
        return AcrylBorders.getTableHeaderBorder();
    }

    public Border getScrollPaneBorder() {
        return AcrylBorders.getScrollPaneBorder();
    }

    public Border getTabbedPaneBorder() {
        return AcrylBorders.getTabbedPaneBorder();
    }

    public Border getMenuBarBorder() {
        return AcrylBorders.getMenuBarBorder();
    }

    public Border getMenuItemBorder() {
        return AcrylBorders.getMenuItemBorder();
    }

    public Border getPopupMenuBorder() {
        return AcrylBorders.getPopupMenuBorder();
    }

    public Border getInternalFrameBorder() {
        return AcrylBorders.getInternalFrameBorder();
    }

    public Border getPaletteBorder() {
        return AcrylBorders.getPaletteBorder();
    }

    public Border getToolBarBorder() {
        return AcrylBorders.getToolBarBorder();
    }

    public Border getProgressBarBorder() {
        return AcrylBorders.getProgressBarBorder();
    }

    public Border getDesktopIconBorder() {
        return AcrylBorders.getDesktopIconBorder();
    }
}

