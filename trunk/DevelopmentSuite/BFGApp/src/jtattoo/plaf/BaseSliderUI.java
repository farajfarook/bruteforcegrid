/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package jtattoo.plaf;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 * @author Michael Hagen
 */
public class BaseSliderUI extends BasicSliderUI {

    protected boolean isRollover = false;

    public BaseSliderUI(JSlider slider) {
        super(slider);
    }

    public static ComponentUI createUI(JComponent c) {
        return new BaseSliderUI((JSlider) c);
    }

    public TrackListener createTrackListener(JSlider slider) {
        return new MyTrackListener();
    }

    public Icon getThumbHorIcon() {
        if (UIManager.getLookAndFeel() instanceof AbstractLookAndFeel) {
            return ((AbstractLookAndFeel) UIManager.getLookAndFeel()).getIconFactory().getThumbHorIcon();
        }
        return null;
    }

    public Icon getThumbHorIconRollover() {
        if (UIManager.getLookAndFeel() instanceof AbstractLookAndFeel) {
            return ((AbstractLookAndFeel) UIManager.getLookAndFeel()).getIconFactory().getThumbHorIconRollover();
        }
        return null;
    }

    public Icon getThumbVerIcon() {
        if (UIManager.getLookAndFeel() instanceof AbstractLookAndFeel) {
            return ((AbstractLookAndFeel) UIManager.getLookAndFeel()).getIconFactory().getThumbVerIcon();
        }
        return null;
    }

    public Icon getThumbVerIconRollover() {
        if (UIManager.getLookAndFeel() instanceof AbstractLookAndFeel) {
            return ((AbstractLookAndFeel) UIManager.getLookAndFeel()).getIconFactory().getThumbVerIconRollover();
        }
        return null;
    }

    protected int getTrackWidth() {
        if (slider.getOrientation() == JSlider.HORIZONTAL) {
            return (thumbRect.height - 9);
        } else {
            return (thumbRect.width - 9);
        }
    }

    protected Dimension getThumbSize() {
        Dimension size = super.getThumbSize();
        if ((getThumbHorIcon() != null) && (getThumbVerIcon() != null)) {
            if (slider.getOrientation() == JSlider.HORIZONTAL) {
                size.width = getThumbHorIcon().getIconWidth();
                size.height = getThumbHorIcon().getIconHeight();
            } else {
                size.width = getThumbVerIcon().getIconWidth();
                size.height = getThumbVerIcon().getIconHeight();
            }
        }
        return size;
    }

    public void paint(Graphics g, JComponent c) {
        paintBackground(g, c);
        recalculateIfInsetsChanged();
        recalculateIfOrientationChanged();
        Rectangle clip = g.getClipBounds();
        if (slider.getPaintTrack() && clip.intersects(trackRect)) {
            paintTrack(g);
        }
        if (slider.getPaintTicks() && clip.intersects(tickRect)) {
            paintTicks(g);
        }
        if (slider.getPaintLabels() && clip.intersects(labelRect)) {
            paintLabels(g);
        }
        if (slider.hasFocus() && clip.intersects(focusRect)) {
            paintFocus(g);
        }
        if (clip.intersects(thumbRect)) {
            paintThumb(g);
        }
    }

    public void paintBackground(Graphics g, JComponent c) {
        if (c.getBackground() instanceof ColorUIResource) {
            g.setColor(AbstractLookAndFeel.getBackgroundColor());
        } else {
            g.setColor(c.getBackground());
        }
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }

    public void paintTrack(Graphics g) {
        boolean leftToRight = JTattooUtilities.isLeftToRight(slider);

        g.translate(trackRect.x, trackRect.y);
        int overhang = 4;
        int trackLeft = 0;
        int trackTop = 0;
        int trackRight = 0;
        int trackBottom = 0;

        if (slider.getOrientation() == JSlider.HORIZONTAL) {
            trackBottom = (trackRect.height - 1) - overhang;
            trackTop = trackBottom - (getTrackWidth() - 1);
            trackRight = trackRect.width - 1;
        } else {
            if (leftToRight) {
                trackLeft = (trackRect.width - overhang) - getTrackWidth();
                trackRight = (trackRect.width - overhang) - 1;
            } else {
                trackLeft = overhang;
                trackRight = overhang + getTrackWidth() - 1;
            }
            trackBottom = trackRect.height - 1;
        }

        g.setColor(AbstractLookAndFeel.getFrameColor());
        g.drawRect(trackLeft, trackTop, (trackRight - trackLeft) - 1, (trackBottom - trackTop) - 1);

        int middleOfThumb = 0;
        int fillTop = 0;
        int fillLeft = 0;
        int fillBottom = 0;
        int fillRight = 0;

        if (slider.getOrientation() == JSlider.HORIZONTAL) {
            middleOfThumb = thumbRect.x + (thumbRect.width / 2);
            middleOfThumb -= trackRect.x;
            fillTop = trackTop + 1;
            fillBottom = trackBottom - 2;

            if (!drawInverted()) {
                fillLeft = trackLeft + 1;
                fillRight = middleOfThumb;
            } else {
                fillLeft = middleOfThumb;
                fillRight = trackRight - 2;
            }
            if (slider.isEnabled()) {
                Color colors[] = null;
                if (!JTattooUtilities.isActive(slider)) {
                    colors = AbstractLookAndFeel.getTheme().getInActiveColors();
                } else {
                    colors = AbstractLookAndFeel.getTheme().getSliderColors();
                }
                JTattooUtilities.fillHorGradient(g, colors, fillLeft + 2, fillTop + 2, fillRight - fillLeft - 2, fillBottom - fillTop - 2);
                Color cHi = ColorHelper.darker(colors[colors.length - 1], 5);
                Color cLo = ColorHelper.darker(colors[colors.length - 1], 10);
                JTattooUtilities.draw3DBorder(g, cHi, cLo, fillLeft + 1, fillTop + 1, fillRight - fillLeft - 1, fillBottom - fillTop - 1);
            } else {
                g.setColor(slider.getBackground());
                g.fillRect(fillLeft, fillTop, fillRight - fillLeft, fillBottom - fillTop);
            }
        } else {
            middleOfThumb = thumbRect.y + (thumbRect.height / 2);
            middleOfThumb -= trackRect.y;
            fillLeft = trackLeft + 1;
            fillRight = trackRight - 2;

            if (!drawInverted()) {
                fillTop = middleOfThumb;
                fillBottom = trackBottom - 2;
            } else {
                fillTop = trackTop + 1;
                fillBottom = middleOfThumb;
            }
            if (slider.isEnabled()) {
                Color colors[] = null;
                if (!JTattooUtilities.isActive(slider)) {
                    colors = AbstractLookAndFeel.getTheme().getInActiveColors();
                } else {
                    colors = AbstractLookAndFeel.getTheme().getSliderColors();
                }
                JTattooUtilities.fillVerGradient(g, colors, fillLeft + 2, fillTop + 2, fillRight - fillLeft - 2, fillBottom - fillTop - 2);
                Color cHi = ColorHelper.darker(colors[colors.length - 1], 5);
                Color cLo = ColorHelper.darker(colors[colors.length - 1], 10);
                JTattooUtilities.draw3DBorder(g, cHi, cLo, fillLeft + 1, fillTop + 1, fillRight - fillLeft - 1, fillBottom - fillTop - 1);
            } else {
                g.setColor(slider.getBackground());
                g.fillRect(fillLeft, fillTop, fillRight - fillLeft + 1, fillBottom - fillTop + 1);
            }
        }
        g.translate(-trackRect.x, -trackRect.y);
    }

    public void paintTicks(Graphics g) {
        boolean leftToRight = JTattooUtilities.isLeftToRight(slider);
        Rectangle tickBounds = tickRect;
        g.setColor(AbstractLookAndFeel.getForegroundColor());
        if (slider.getOrientation() == JSlider.HORIZONTAL) {
            g.translate(0, tickBounds.y);

            int value = slider.getMinimum();
            int xPos = 0;

            if (slider.getMinorTickSpacing() > 0) {
                while (value <= slider.getMaximum()) {
                    xPos = xPositionForValue(value);
                    paintMinorTickForHorizSlider(g, tickBounds, xPos);
                    value += slider.getMinorTickSpacing();
                }
            }

            if (slider.getMajorTickSpacing() > 0) {
                value = slider.getMinimum();
                while (value <= slider.getMaximum()) {
                    xPos = xPositionForValue(value);
                    paintMajorTickForHorizSlider(g, tickBounds, xPos);
                    value += slider.getMajorTickSpacing();
                }
            }

            g.translate(0, -tickBounds.y);
        } else {
            g.translate(tickBounds.x, 0);

            int value = slider.getMinimum();
            int yPos = 0;

            if (slider.getMinorTickSpacing() > 0) {
                int offset = 0;
                if (!leftToRight) {
                    offset = tickBounds.width - tickBounds.width / 2;
                    g.translate(offset, 0);
                }

                while (value <= slider.getMaximum()) {
                    yPos = yPositionForValue(value);
                    paintMinorTickForVertSlider(g, tickBounds, yPos);
                    value += slider.getMinorTickSpacing();
                }
                if (!leftToRight) {
                    g.translate(-offset, 0);
                }
            }

            if (slider.getMajorTickSpacing() > 0) {
                value = slider.getMinimum();
                if (!leftToRight) {
                    g.translate(2, 0);
                }

                while (value <= slider.getMaximum()) {
                    yPos = yPositionForValue(value);
                    paintMajorTickForVertSlider(g, tickBounds, yPos);
                    value += slider.getMajorTickSpacing();
                }

                if (!leftToRight) {
                    g.translate(-2, 0);
                }
            }
            g.translate(-tickBounds.x, 0);
        }
    }

    public void paintThumb(Graphics g) {
        Icon icon = null;
        if (slider.getOrientation() == JSlider.HORIZONTAL) {
            if (isRollover && slider.isEnabled()) {
                icon = getThumbHorIconRollover();
            } else if (slider.isEnabled()) {
                icon = getThumbHorIcon();
            }
        } else {
            if (isRollover && slider.isEnabled()) {
                icon = getThumbVerIconRollover();
            } else if (slider.isEnabled()) {
                icon = getThumbVerIcon();
            }
        }
        if (icon != null) {
            icon.paintIcon(null, g, thumbRect.x, thumbRect.y);
        }
    }

    protected class MyTrackListener extends TrackListener {

        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            isRollover = thumbRect.contains(e.getPoint());
            paintThumb(slider.getGraphics());
        }

        public void mouseMoved(MouseEvent e) {
            super.mouseEntered(e);
            boolean rollover = thumbRect.contains(e.getPoint());
            if (rollover != isRollover) {
                isRollover = rollover;
                paintThumb(slider.getGraphics());
            }
        }

        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            isRollover = false;
            paintThumb(slider.getGraphics());
        }
    }
}
