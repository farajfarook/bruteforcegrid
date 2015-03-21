/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package jtattoo.plaf;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * @author Michael Hagen
 */
public class BaseIcons {

    private static Icon checkBoxIcon = null;
    private static Icon radioButtonIcon = null;
    private static Icon optionPaneErrorIcon = null;
    private static Icon optionPaneWarningIcon = null;
    private static Icon optionPaneInformationIcon = null;
    private static Icon optionPaneQuestionIcon = null;
    private static Icon fileChooserDetailViewIcon = null;
    private static Icon fileChooserHomeFolderIcon = null;
    private static Icon fileChooserListViewIcon = null;
    private static Icon fileChooserNewFolderIcon = null;
    private static Icon fileChooserUpFolderIcon = null;
    private static Icon treeComputerIcon = null;
    private static Icon treeFloppyDriveIcon = null;
    private static Icon treeHardDriveIcon = null;
    private static Icon treeFolderIcon = null;
    private static Icon treeLeafIcon = null;
    private static Icon treeOpenIcon = null;
    private static Icon treeClosedIcon = null;
    private static Icon paletteCloseIcon = null;
    private static Icon menuIcon = null;
    private static Icon iconIcon = null;
    private static Icon maxIcon = null;
    private static Icon minIcon = null;
    private static Icon closeIcon = null;
    private static Icon upArrowIcon = null;
    private static Icon downArrowIcon = null;
    private static Icon leftArrowIcon = null;
    private static Icon rightArrowIcon = null;
    private static Icon splitterUpArrowIcon = null;
    private static Icon splitterDownArrowIcon = null;
    private static Icon splitterLeftArrowIcon = null;
    private static Icon splitterRightArrowIcon = null;
    private static Icon splitterHorBumpIcon = null;
    private static Icon splitterVerBumpIcon = null;
    private static Icon thumbHorIcon = null;
    private static Icon thumbVerIcon = null;
    private static Icon thumbHorIconRollover = null;
    private static Icon thumbVerIconRollover = null;

    public static Icon getRadioButtonIcon() {
        if (radioButtonIcon == null) {
            radioButtonIcon = new RadioButtonIcon();
        }
        return radioButtonIcon;
    }

    public static Icon getCheckBoxIcon() {
        if (checkBoxIcon == null) {
            checkBoxIcon = new CheckBoxIcon();
        }
        return checkBoxIcon;
    }

    // OptionPane
    public static Icon getOptionPaneErrorIcon() {
        if (optionPaneErrorIcon == null) {
            optionPaneErrorIcon = new LazyImageIcon("icons/Error.gif");
        }
        return optionPaneErrorIcon;
    }

    public static Icon getOptionPaneWarningIcon() {
        if (optionPaneWarningIcon == null) {
            optionPaneWarningIcon = new LazyImageIcon("icons/Warning.gif");
        }
        return optionPaneWarningIcon;
    }

    public static Icon getOptionPaneInformationIcon() {
        if (optionPaneInformationIcon == null) {
            optionPaneInformationIcon = new LazyImageIcon("icons/Inform.gif");
        }
        return optionPaneInformationIcon;
    }

    public static Icon getOptionPaneQuestionIcon() {
        if (optionPaneQuestionIcon == null) {
            optionPaneQuestionIcon = new LazyImageIcon("icons/Question.gif");
        }
        return optionPaneQuestionIcon;
    }

    // FileChooser
    public static Icon getFileChooserDetailViewIcon() {
        if (fileChooserDetailViewIcon == null) {
            fileChooserDetailViewIcon = new LazyImageIcon("icons/DetailsView.gif");
        }
        return fileChooserDetailViewIcon;
    }

    public static Icon getFileChooserHomeFolderIcon() {
        if (fileChooserHomeFolderIcon == null) {
            fileChooserHomeFolderIcon = new LazyImageIcon("icons/Home.gif");
        }
        return fileChooserHomeFolderIcon;
    }

    public static Icon getFileChooserListViewIcon() {
        if (fileChooserListViewIcon == null) {
            fileChooserListViewIcon = new LazyImageIcon("icons/ListView.gif");
        }
        return fileChooserListViewIcon;
    }

    public static Icon getFileChooserNewFolderIcon() {
        if (fileChooserNewFolderIcon == null) {
            fileChooserNewFolderIcon = new LazyImageIcon("icons/NewFolder.gif");
        }
        return fileChooserNewFolderIcon;
    }

    public static Icon getFileChooserUpFolderIcon() {
        if (fileChooserUpFolderIcon == null) {
            fileChooserUpFolderIcon = new LazyImageIcon("icons/UpFolder.gif");
        }
        return fileChooserUpFolderIcon;
    }

    // Tree
    public static Icon getTreeComputerIcon() {
        if (treeComputerIcon == null) {
            treeComputerIcon = new LazyImageIcon("icons/Computer.gif");
        }
        return treeComputerIcon;
    }

    public static Icon getTreeFloppyDriveIcon() {
        if (treeFloppyDriveIcon == null) {
            treeFloppyDriveIcon = new LazyImageIcon("icons/FloppyDrive.gif");
        }
        return treeFloppyDriveIcon;
    }

    public static Icon getTreeHardDriveIcon() {
        if (treeHardDriveIcon == null) {
            treeHardDriveIcon = new LazyImageIcon("icons/HardDrive.gif");
        }
        return treeHardDriveIcon;
    }

    public static Icon getTreeFolderIcon() {
        if (treeFolderIcon == null) {
            treeFolderIcon = new LazyImageIcon("icons/TreeClosed.gif");
        }
        return treeFolderIcon;
    }

    public static Icon getTreeLeafIcon() {
        if (treeLeafIcon == null) {
            treeLeafIcon = new LazyImageIcon("icons/TreeLeaf.gif");
        }
        return treeLeafIcon;
    }

    public static Icon getTreeControlIcon(boolean isCollapsed) {
        if (isCollapsed) {
            if (treeClosedIcon == null) {
                treeClosedIcon = new LazyImageIcon("icons/TreeClosedButton.gif");
            }
            return treeClosedIcon;
        } else {
            if (treeOpenIcon == null) {
                treeOpenIcon = new LazyImageIcon("icons/TreeOpenButton.gif");
            }
            return treeOpenIcon;
        }
    }

    // TitlePane icons
    public static Icon getMenuIcon() {
        if (menuIcon == null) {
            menuIcon = new LazyImageIcon("icons/JavaCup.gif");
        }
        return menuIcon;
    }

    public static Icon getIconIcon() {
        if (iconIcon == null) {
            iconIcon = new IconSymbol(Color.black, null, Color.red);
        }
        return iconIcon;
    }

    public static Icon getMaxIcon() {
        if (maxIcon == null) {
            maxIcon = new MaxSymbol(Color.black, null, Color.red);
        }
        return maxIcon;
    }

    public static Icon getMinIcon() {
        if (minIcon == null) {
            minIcon = new MinSymbol(Color.black, null, Color.red);
        }
        return minIcon;
    }

    public static Icon getCloseIcon() {
        if (closeIcon == null) {
            closeIcon = new CloseSymbol(Color.black, null, Color.red);
        }
        return closeIcon;
    }

    public static Icon getPaletteCloseIcon() {
        if (paletteCloseIcon == null) {
            paletteCloseIcon = new CloseSymbol(Color.black, null, Color.red);
        }
        return paletteCloseIcon;
    }

    // MenuIcons
    public static Icon getMenuArrowIcon() {
        return getRightArrowIcon();
    }

    public static Icon getMenuCheckBoxIcon() {
        return getCheckBoxIcon();
    }

    public static Icon getMenuRadioButtonIcon() {
        return getRadioButtonIcon();
    }

    // ArrowIcons
    public static Icon getUpArrowIcon() {
        if (upArrowIcon == null) {
            upArrowIcon = new LazyImageIcon("icons/UpArrow.gif");
        }
        return upArrowIcon;
    }

    public static Icon getDownArrowIcon() {
        if (downArrowIcon == null) {
            downArrowIcon = new LazyImageIcon("icons/DownArrow.gif");
        }
        return downArrowIcon;
    }

    public static Icon getLeftArrowIcon() {
        if (leftArrowIcon == null) {
            leftArrowIcon = new LazyImageIcon("icons/LeftArrow.gif");
        }
        return leftArrowIcon;
    }

    public static Icon getRightArrowIcon() {
        if (rightArrowIcon == null) {
            rightArrowIcon = new LazyImageIcon("icons/RightArrow.gif");
        }
        return rightArrowIcon;
    }

    public static Icon getSplitterUpArrowIcon() {
        if (splitterUpArrowIcon == null) {
            splitterUpArrowIcon = new LazyImageIcon("icons/SplitterUpArrow.gif");
        }
        return splitterUpArrowIcon;
    }

    public static Icon getSplitterDownArrowIcon() {
        if (splitterDownArrowIcon == null) {
            splitterDownArrowIcon = new LazyImageIcon("icons/SplitterDownArrow.gif");
        }
        return splitterDownArrowIcon;
    }

    public static Icon getSplitterLeftArrowIcon() {
        if (splitterLeftArrowIcon == null) {
            splitterLeftArrowIcon = new LazyImageIcon("icons/SplitterLeftArrow.gif");
        }
        return splitterLeftArrowIcon;
    }

    public static Icon getSplitterRightArrowIcon() {
        if (splitterRightArrowIcon == null) {
            splitterRightArrowIcon = new LazyImageIcon("icons/SplitterRightArrow.gif");
        }
        return splitterRightArrowIcon;
    }

    public static Icon getSplitterHorBumpIcon() {
        if (splitterHorBumpIcon == null) {
            splitterHorBumpIcon = new LazyImageIcon("icons/SplitterHorBumps.gif");
        }
        return splitterHorBumpIcon;
    }

    public static Icon getSplitterVerBumpIcon() {
        if (splitterVerBumpIcon == null) {
            splitterVerBumpIcon = new LazyImageIcon("icons/SplitterVerBumps.gif");
        }
        return splitterVerBumpIcon;
    }

    public static Icon getComboBoxIcon() {
        return getDownArrowIcon();
    }

    public static Icon getThumbHorIcon() {
        if (thumbHorIcon == null) {
            thumbHorIcon = new LazyImageIcon("icons/thumb_hor.gif");
        }
        return thumbHorIcon;
    }

    public static Icon getThumbVerIcon() {
        if (thumbVerIcon == null) {
            thumbVerIcon = new LazyImageIcon("icons/thumb_ver.gif");
        }
        return thumbVerIcon;
    }

    public static Icon getThumbHorIconRollover() {
        if (thumbHorIconRollover == null) {
            thumbHorIconRollover = new LazyImageIcon("icons/thumb_hor_rollover.gif");
        }
        return thumbHorIconRollover;
    }

    public static Icon getThumbVerIconRollover() {
        if (thumbVerIconRollover == null) {
            thumbVerIconRollover = new LazyImageIcon("icons/thumb_ver_rollover.gif");
        }
        return thumbVerIconRollover;
    }

//-----------------------------------------------------------------------------------------------------------
    private static class CheckBoxIcon implements Icon {

        private static Icon checkIcon = new LazyImageIcon("icons/CheckSymbol.gif");
        private static Icon checkPressedIcon = new LazyImageIcon("icons/CheckPressedSymbol.gif");

        private static final int WIDTH = 13;
        private static final int HEIGHT = 13;

        public void paintIcon(Component c, Graphics g, int x, int y) {
            if (!JTattooUtilities.isLeftToRight(c)) {
                x += 3;
            }

            AbstractButton b = (AbstractButton) c;
            ButtonModel model = b.getModel();
            if (b.isEnabled() && b.isRolloverEnabled() && model.isRollover() && !model.isArmed()) {
                JTattooUtilities.fillHorGradient(g, AbstractLookAndFeel.getTheme().getRolloverColors(), x, y, WIDTH, HEIGHT);
            } else {
                if (model.isArmed() && model.isPressed()) {
                    g.setColor(AbstractLookAndFeel.getBackgroundColor());
                } else {
                    g.setColor(Color.white);
                }
                g.fillRect(x, y, WIDTH, HEIGHT);
            }
            if (b.isEnabled()) {
                g.setColor(AbstractLookAndFeel.getFrameColor());
            } else {
                g.setColor(ColorHelper.brighter(AbstractLookAndFeel.getFrameColor(), 40));
            }
            g.drawRect(x, y, WIDTH, HEIGHT);

            int xi = x + ((WIDTH - checkIcon.getIconWidth()) / 2) + 1;
            int yi = y + ((HEIGHT - checkIcon.getIconHeight()) / 2) + 1;
            if (model.isPressed() && model.isArmed()) {
                checkPressedIcon.paintIcon(c, g, xi, yi);
            } else if (model.isSelected()) {
                checkIcon.paintIcon(c, g, xi, yi);
            }
        }

        public int getIconWidth() {
            return WIDTH + 4;
        }

        public int getIconHeight() {
            return HEIGHT;
        }
    }

//-----------------------------------------------------------------------------------------------------------
    private static class RadioButtonIcon implements Icon {

        private static final int WIDTH = 12;
        private static final int HEIGHT = 12;

        public void paintIcon(Component c, Graphics g, int x, int y) {
            if (!JTattooUtilities.isLeftToRight(c)) {
                x += 3;
            }
            Graphics2D g2D = (Graphics2D) g;
            AbstractButton b = (AbstractButton) c;
            ButtonModel model = b.getModel();
            if (b.isEnabled() && b.isRolloverEnabled() && model.isRollover() && !model.isArmed()) {
                Shape savedClip = g.getClip();
                Area clipArea = new Area(savedClip);
                Area ellipseArea = new Area(new Ellipse2D.Double(x, y, WIDTH + 1, HEIGHT + 1));
                ellipseArea.intersect(clipArea);
                g2D.setClip(ellipseArea);
                JTattooUtilities.fillHorGradient(g, AbstractLookAndFeel.getTheme().getRolloverColors(), x, y, WIDTH, HEIGHT);
                g2D.setClip(savedClip);
            } else {
                Shape savedClip = g.getClip();
                Area clipArea = new Area(savedClip);
                Area ellipseArea = new Area(new Ellipse2D.Double(x, y, WIDTH + 1, HEIGHT + 1));
                ellipseArea.intersect(clipArea);
                g2D.setClip(ellipseArea);
                g2D.setColor(Color.white);
                g2D.fillRect(x, y, WIDTH, HEIGHT);
                g2D.setClip(savedClip);
            }
            if (b.isEnabled()) {
                g.setColor(AbstractLookAndFeel.getFrameColor());
            } else {
                g.setColor(ColorHelper.brighter(AbstractLookAndFeel.getFrameColor(), 40));
            }
            Object savedRederingHint = g2D.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.drawOval(x, y, WIDTH, HEIGHT);
            if (model.isSelected()) {
                g2D.setColor(Color.black);
                g2D.fillOval(x + 3, y + 3, WIDTH - 5, HEIGHT - 5);
            }
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, savedRederingHint);
        }

        public int getIconWidth() {
            return WIDTH + 4;
        }

        public int getIconHeight() {
            return HEIGHT;
        }
    }

//-----------------------------------------------------------------------------------------------------------
    public static class IconSymbol implements Icon {

        private Color foregroundColor = null;
        private Color shadowColor = null;
        private Color inactiveForegroundColor = null;
        private Color inactiveShadowColor = null;
        private Color rolloverColor = null;
        private Insets insets = new Insets(0, 0, 0, 0);

        public IconSymbol(Color foregroundColor, Color shadowColor, Color rolloverColor) {
            this.foregroundColor = foregroundColor;
            this.shadowColor = shadowColor;
            this.rolloverColor = rolloverColor;
            this.inactiveForegroundColor = foregroundColor;
            this.inactiveShadowColor = shadowColor;
        }

        public IconSymbol(Color foregroundColor, Color shadowColor, Color rolloverColor, Insets insets) {
            this.foregroundColor = foregroundColor;
            this.shadowColor = shadowColor;
            this.rolloverColor = rolloverColor;
            this.inactiveForegroundColor = foregroundColor;
            this.inactiveShadowColor = shadowColor;
            this.insets = insets;
        }

        public IconSymbol(Color foregroundColor, Color shadowColor, Color rolloverColor, Color inactiveForegroundColor, Color inactiveShadowColor, Insets insets) {
            this.foregroundColor = foregroundColor;
            this.shadowColor = shadowColor;
            this.rolloverColor = rolloverColor;
            this.inactiveForegroundColor = inactiveForegroundColor;
            this.inactiveShadowColor = inactiveShadowColor;
            this.insets = insets;
        }

        public int getIconHeight() {
            return 16;
        }

        public int getIconWidth() {
            return 16;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2D = (Graphics2D) g;
            g.translate(insets.left, insets.top);
            int w = c.getWidth() - insets.left - insets.right;
            int h = c.getHeight() - insets.top - insets.bottom;
            boolean active = JTattooUtilities.isActive((JComponent) c);
            Color color = foregroundColor;
            if (!active) {
                color = inactiveForegroundColor;
            }
            if (c instanceof AbstractButton) {
                if (((AbstractButton) c).getModel().isRollover() && (rolloverColor != null)) {
                    color = rolloverColor;
                }
            }
            int lw = (w / 12) + 1;
            int dx = (w / 5) + 2;
            int dy = dx;
            Stroke stroke = g2D.getStroke();
            g2D.setStroke(new BasicStroke(lw, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            if (shadowColor != null) {
                if (!active) {
                    g2D.setColor(inactiveShadowColor);
                } else {
                    g2D.setColor(shadowColor);
                }
                g2D.drawLine(dx + 1, h - dy, w - dx + 1, h - dy);
            }
            g2D.setColor(color);
            g2D.drawLine(dx, h - dy - 1, w - dx, h - dy - 1);
            g2D.setStroke(stroke);
            g.translate(-insets.left, -insets.top);
        }
    }

//-----------------------------------------------------------------------------------------------------------
    public static class MaxSymbol implements Icon {

        private Color foregroundColor = null;
        private Color shadowColor = null;
        private Color rolloverColor = null;
        private Color inactiveForegroundColor = null;
        private Color inactiveShadowColor = null;
        private Insets insets = new Insets(0, 0, 0, 0);

        public MaxSymbol(Color foregroundColor, Color shadowColor, Color rolloverColor) {
            this.foregroundColor = foregroundColor;
            this.shadowColor = shadowColor;
            this.rolloverColor = rolloverColor;
            this.inactiveForegroundColor = foregroundColor;
            this.inactiveShadowColor = shadowColor;
        }

        public MaxSymbol(Color foregroundColor, Color shadowColor, Color rolloverColor, Insets insets) {
            this.foregroundColor = foregroundColor;
            this.shadowColor = shadowColor;
            this.rolloverColor = rolloverColor;
            this.inactiveForegroundColor = foregroundColor;
            this.inactiveShadowColor = shadowColor;
            this.insets = insets;
        }

        public MaxSymbol(Color foregroundColor, Color shadowColor, Color rolloverColor, Color inactiveForegroundColor, Color inactiveShadowColor, Insets insets) {
            this.foregroundColor = foregroundColor;
            this.shadowColor = shadowColor;
            this.rolloverColor = rolloverColor;
            this.inactiveForegroundColor = inactiveForegroundColor;
            this.inactiveShadowColor = inactiveShadowColor;
            this.insets = insets;
        }

        public int getIconHeight() {
            return 16;
        }

        public int getIconWidth() {
            return 16;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2D = (Graphics2D) g;
            g.translate(insets.left, insets.top);
            int w = c.getWidth() - insets.left - insets.right;
            int h = c.getHeight() - insets.top - insets.bottom;
            boolean active = JTattooUtilities.isActive((JComponent) c);
            Color color = foregroundColor;
            if (!active) {
                color = inactiveForegroundColor;
            }
            if (c instanceof AbstractButton) {
                if (((AbstractButton) c).getModel().isRollover() && (rolloverColor != null)) {
                    color = rolloverColor;
                }
            }
            int lw = (w / 12);
            int dx = (w / 5) + 2;
            int dy = dx;

            Stroke stroke = g2D.getStroke();
            g2D.setStroke(new BasicStroke(lw, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            if (shadowColor != null) {
                if (!active) {
                    g2D.setColor(inactiveShadowColor);
                } else {
                    g2D.setColor(shadowColor);
                }
                g2D.drawRect(dx + 1, dy + 1, w - (2 * dx), h - (2 * dy));
                g2D.drawLine(dx + 1, dy + lw + 1, w - dx, dy + lw + 1);
            }
            g2D.setColor(color);
            g2D.drawRect(dx, dy, w - (2 * dx), h - (2 * dy));
            g2D.drawLine(dx, dy + lw, w - dx - 1, dy + lw);
            g2D.setStroke(stroke);
            g.translate(-insets.left, -insets.top);
        }
    }

//-----------------------------------------------------------------------------------------------------------
    public static class MinSymbol implements Icon {

        private Color foregroundColor = null;
        private Color shadowColor = null;
        private Color rolloverColor = null;
        private Color inactiveForegroundColor = null;
        private Color inactiveShadowColor = null;
        private Insets insets = new Insets(0, 0, 0, 0);

        public MinSymbol(Color foregroundColor, Color shadowColor, Color rolloverColor) {
            this.foregroundColor = foregroundColor;
            this.shadowColor = shadowColor;
            this.rolloverColor = rolloverColor;
            this.inactiveForegroundColor = foregroundColor;
            this.inactiveShadowColor = shadowColor;
        }

        public MinSymbol(Color foregroundColor, Color shadowColor, Color rolloverColor, Insets insets) {
            this.foregroundColor = foregroundColor;
            this.shadowColor = shadowColor;
            this.rolloverColor = rolloverColor;
            this.inactiveForegroundColor = foregroundColor;
            this.inactiveShadowColor = shadowColor;
            this.insets = insets;
        }

        public MinSymbol(Color foregroundColor, Color shadowColor, Color rolloverColor, Color inactiveForegroundColor, Color inactiveShadowColor, Insets insets) {
            this.foregroundColor = foregroundColor;
            this.shadowColor = shadowColor;
            this.rolloverColor = rolloverColor;
            this.inactiveForegroundColor = inactiveForegroundColor;
            this.inactiveShadowColor = inactiveShadowColor;
            this.insets = insets;
        }

        public int getIconHeight() {
            return 16;
        }

        public int getIconWidth() {
            return 16;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2D = (Graphics2D) g;
            g.translate(insets.left, insets.top);
            int w = c.getWidth() - insets.left - insets.right;
            int h = c.getHeight() - insets.top - insets.bottom;
            int m = Math.min(w, h);
            int d = 10;
            int lw = 1;
            if (m < 14) {
                d = 7;
            } else if (m < 18) {
                d = 9;
            } else if (m < 22) {
                d = 11;
            } else if (m < 26) {
                d = 13;
            } else {
                d = 15;
            //lw = 2;
            }

            w = Math.min(w, h);
            h = Math.min(w, h);
            w -= d;
            h -= d;
            x += (d / 4) + 1;
            y += (d / 4) + 1;

            boolean active = JTattooUtilities.isActive((JComponent) c);
            Color color = foregroundColor;
            if (!active) {
                color = inactiveForegroundColor;
            }
            if (c instanceof AbstractButton) {
                if (((AbstractButton) c).getModel().isRollover() && (rolloverColor != null)) {
                    color = rolloverColor;
                }
            }
            Stroke stroke = g2D.getStroke();
            g2D.setStroke(new BasicStroke(lw, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            Shape clipShape = g.getClip();
            Area clipArea = new Area(clipShape);
            int xc = x + (d / 2) - 2;
            int yc = x + Math.max((d / 2) - 2, 2 * lw + 1);
            clipArea.subtract(new Area(new Rectangle2D.Double(xc, yc, w, h)));
            g2D.setClip(clipArea);
            if (shadowColor != null) {
                if (!active) {
                    g2D.setColor(inactiveShadowColor);
                } else {
                    g2D.setColor(shadowColor);
                }
                g2D.drawRect(x + 1, y + 1, w, h);
                g2D.drawLine(x + 1, y + lw + 1, x + w + 1, y + lw + 1);
            }
            g2D.setColor(color);
            g2D.drawRect(x, y, w, h);
            g2D.drawLine(x, y + lw, x + w, y + lw);
            g2D.setClip(clipShape);

            x += (d / 2) - 2;
            y += Math.max((d / 2) - 2, 2 * lw + 1);
            if (shadowColor != null) {
                if (!active) {
                    g2D.setColor(inactiveShadowColor);
                } else {
                    g2D.setColor(shadowColor);
                }
                g2D.drawRect(x + 1, y + 1, w, h);
                g2D.drawLine(x + 1, y + lw + 1, x + w + 1, y + lw + 1);
            }
            g2D.setColor(color);
            g2D.drawRect(x, y, w, h);
            g2D.drawLine(x, y + lw, x + w, y + lw);

            g2D.setStroke(stroke);
            g.translate(-insets.left, -insets.top);
        }
    }

//-----------------------------------------------------------------------------------------------------------
    public static class CloseSymbol implements Icon {

        private Color foregroundColor = null;
        private Color shadowColor = null;
        private Color rolloverColor = null;
        private Color inactiveForegroundColor = null;
        private Color inactiveShadowColor = null;
        private Insets insets = new Insets(0, 0, 0, 0);

        public CloseSymbol(Color foregroundColor, Color shadowColor, Color rolloverColor) {
            this.foregroundColor = foregroundColor;
            this.shadowColor = shadowColor;
            this.rolloverColor = rolloverColor;
            this.inactiveForegroundColor = foregroundColor;
            this.inactiveShadowColor = shadowColor;
        }

        public CloseSymbol(Color foregroundColor, Color shadowColor, Color rolloverColor, Insets insets) {
            this.foregroundColor = foregroundColor;
            this.shadowColor = shadowColor;
            this.rolloverColor = rolloverColor;
            this.insets = insets;
            this.inactiveForegroundColor = foregroundColor;
            this.inactiveShadowColor = shadowColor;
        }

        public CloseSymbol(Color foregroundColor, Color shadowColor, Color rolloverColor, Color inactiveForegroundColor, Color inactiveShadowColor, Insets insets) {
            this.foregroundColor = foregroundColor;
            this.shadowColor = shadowColor;
            this.rolloverColor = rolloverColor;
            this.inactiveForegroundColor = inactiveForegroundColor;
            this.inactiveShadowColor = inactiveShadowColor;
            this.insets = insets;
        }

        public int getIconHeight() {
            return 16;
        }

        public int getIconWidth() {
            return 16;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2D = (Graphics2D) g;
            g.translate(insets.left, insets.top);
            int w = c.getWidth() - insets.left - insets.right;
            int h = c.getHeight() - insets.top - insets.bottom;
            boolean active = JTattooUtilities.isActive((JComponent) c);
            Color color = foregroundColor;
            if (!active) {
                color = inactiveForegroundColor;
            }
            if (c instanceof AbstractButton) {
                if (((AbstractButton) c).getModel().isRollover() && (rolloverColor != null)) {
                    color = rolloverColor;
                }
            }
            int lw = (w / 12) + 1;
            int dx = (w / 5) + 2;
            int dy = dx;

            Stroke stroke = g2D.getStroke();
            Object savedRederingHint = g2D.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setStroke(new BasicStroke(lw, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
            if (shadowColor != null) {
                if (!active) {
                    g2D.setColor(inactiveShadowColor);
                } else {
                    g2D.setColor(shadowColor);
                }
                g2D.drawLine(dx + 1, dy + 1, w - dx + 1, h - dy + 1);
                g2D.drawLine(w - dx + 1, dy + 1, dx + 1, h - dy + 1);
            }
            g2D.setColor(color);
            g2D.drawLine(dx, dy, w - dx, h - dy);
            g2D.drawLine(w - dx, dy, dx, h - dy);
            g2D.setStroke(stroke);
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, savedRederingHint);
            g.translate(-insets.left, -insets.top);
        }
    }
}
