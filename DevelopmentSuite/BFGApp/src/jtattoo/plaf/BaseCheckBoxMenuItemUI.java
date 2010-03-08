/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package jtattoo.plaf;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.*;

/**
 * @author Michael Hagen
 */
public class BaseCheckBoxMenuItemUI extends BaseMenuItemUI {

    public static ComponentUI createUI(JComponent c) {
        return new BaseCheckBoxMenuItemUI();
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        c.setOpaque(false);
    }

    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        c.setOpaque(true);
    }

    protected void installDefaults() {
        super.installDefaults();
        checkIcon = UIManager.getIcon("CheckBoxMenuItem.checkIcon");
    }

    public void update(Graphics g, JComponent c) {
        paintBackground(g, c, 0, 0, c.getWidth(), c.getHeight());
        paint(g, c);
    }

    protected void paintBackground(Graphics g, JComponent c, int x, int y, int w, int h) {
        JMenuItem b = (JMenuItem) c;
        ButtonModel model = b.getModel();
        if (model.isArmed() || (c instanceof JMenu && model.isSelected())) {
            g.setColor(AbstractLookAndFeel.getMenuSelectionBackgroundColor());
            g.fillRect(x, y, w, h);
        } else if (!AbstractLookAndFeel.getTheme().isMenuOpaque()) {
            Graphics2D g2D = (Graphics2D) g;
            Composite composite = g2D.getComposite();
            AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, AbstractLookAndFeel.getTheme().getMenuAlpha());
            g2D.setComposite(alpha);
            g.setColor(AbstractLookAndFeel.getMenuBackgroundColor());
            g.fillRect(x, y, w, h);
            g2D.setComposite(composite);
        } else {
            g.setColor(AbstractLookAndFeel.getMenuBackgroundColor());
            g.fillRect(x, y, w, h);
        }
        g.setColor(AbstractLookAndFeel.getMenuForegroundColor());
    }
}
