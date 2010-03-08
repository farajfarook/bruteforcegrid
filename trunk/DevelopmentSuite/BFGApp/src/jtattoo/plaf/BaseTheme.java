/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package jtattoo.plaf;

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.MetalTheme;

public class BaseTheme extends MetalTheme {

    public static final int TEXT_ANTIALIAS_DEFAULT = 0;
    public static final int TEXT_ANTIALIAS_GRAY = 1;
    public static final int TEXT_ANTIALIAS_HRGB = 2;
    public static final int TEXT_ANTIALIAS_HBGR = 3;
    public static final int TEXT_ANTIALIAS_VRGB = 4;
    public static final int TEXT_ANTIALIAS_VBGR = 5;
    private static final String DIALOG = "Dialog";
    public static final ColorUIResource red = new ColorUIResource(255, 0, 0);
    public static final ColorUIResource green = new ColorUIResource(0, 255, 255);
    public static final ColorUIResource cyan = new ColorUIResource(0, 255, 255);
    public static final ColorUIResource white = new ColorUIResource(255, 255, 255);
    public static final ColorUIResource superLightGray = new ColorUIResource(248, 248, 248);
    public static final ColorUIResource extraLightGray = new ColorUIResource(232, 232, 232);
    public static final ColorUIResource lightGray = new ColorUIResource(196, 196, 196);
    public static final ColorUIResource gray = new ColorUIResource(164, 164, 164);
    public static final ColorUIResource darkGray = new ColorUIResource(148, 148, 148);
    public static final ColorUIResource extraDarkGray = new ColorUIResource(96, 96, 96);
    public static final ColorUIResource black = new ColorUIResource(0, 0, 0);
    public static final ColorUIResource orange = new ColorUIResource(255, 200, 0);
    public static final ColorUIResource yellow = new ColorUIResource(255, 255, 164);
    public static final ColorUIResource blue = new ColorUIResource(0, 128, 255);
    public static final ColorUIResource darkBlue = new ColorUIResource(0, 64, 128);
    protected static String internalName = "Default";
    protected static boolean windowDecoration = false;
    protected static boolean dynamicLayout = false;
    protected static boolean textAntiAliasing = false;
    protected static int textAntiAliasingMode = TEXT_ANTIALIAS_HRGB;
    protected static boolean backgroundPattern = true;
    protected static boolean menuOpaque = true;
    protected static float menuAlpha = 0.9f;
    protected static String logoString = "JTattoo";
    protected static FontUIResource controlFont = null;
    protected static FontUIResource systemFont = null;
    protected static FontUIResource userFont = null;
    protected static FontUIResource smallFont = null;
    protected static FontUIResource menuFont = null;
    protected static FontUIResource windowTitleFont = null;
    protected static ColorUIResource foregroundColor = null;
    protected static ColorUIResource disabledForegroundColor = null;
    protected static ColorUIResource disabledBackgroundColor = null;
    protected static ColorUIResource backgroundColor = null;
    protected static ColorUIResource backgroundColorLight = null;
    protected static ColorUIResource backgroundColorDark = null;
    protected static ColorUIResource inputBackgroundColor = null;
    protected static ColorUIResource inputForegroundColor = null;
    protected static ColorUIResource selectionForegroundColor = null;
    protected static ColorUIResource selectionBackgroundColor = null;
    protected static ColorUIResource rolloverColor = null;
    protected static ColorUIResource rolloverColorLight = null;
    protected static ColorUIResource rolloverColorDark = null;
    protected static ColorUIResource focusColor = null;
    protected static ColorUIResource focusCellColor = null;
    protected static ColorUIResource frameColor = null;
    protected static ColorUIResource gridColor = null;
    protected static ColorUIResource buttonForegroundColor = null;
    protected static ColorUIResource buttonBackgroundColor = null;
    protected static ColorUIResource buttonColorLight = null;
    protected static ColorUIResource buttonColorDark = null;
    protected static ColorUIResource controlForegroundColor = null;
    protected static ColorUIResource controlBackgroundColor = null;
    protected static ColorUIResource controlHighlightColor = null;
    protected static ColorUIResource controlShadowColor = null;
    protected static ColorUIResource controlDarkShadowColor = null;
    protected static ColorUIResource controlColorLight = null;
    protected static ColorUIResource controlColorDark = null;
    protected static ColorUIResource windowTitleForegroundColor = null;
    protected static ColorUIResource windowTitleBackgroundColor = null;
    protected static ColorUIResource windowTitleColorLight = null;
    protected static ColorUIResource windowTitleColorDark = null;
    protected static ColorUIResource windowBorderColor = null;
    protected static ColorUIResource windowInactiveTitleForegroundColor = null;
    protected static ColorUIResource windowInactiveTitleBackgroundColor = null;
    protected static ColorUIResource windowInactiveTitleColorLight = null;
    protected static ColorUIResource windowInactiveTitleColorDark = null;
    protected static ColorUIResource windowInactiveBorderColor = null;
    protected static ColorUIResource menuForegroundColor = null;
    protected static ColorUIResource menuBackgroundColor = null;
    protected static ColorUIResource menuSelectionForegroundColor = null;
    protected static ColorUIResource menuSelectionBackgroundColor = null;
    protected static ColorUIResource menuColorLight = null;
    protected static ColorUIResource menuColorDark = null;
    protected static ColorUIResource toolbarForegroundColor = null;
    protected static ColorUIResource toolbarBackgroundColor = null;
    protected static ColorUIResource toolbarColorLight = null;
    protected static ColorUIResource toolbarColorDark = null;
    protected static ColorUIResource desktopColor = null;
    protected static ColorUIResource tooltipForegroundColor = null;
    protected static ColorUIResource tooltipBackgroundColor = null;
    protected static Color DEFAULT_COLORS[] = null;
    protected static Color HIDEFAULT_COLORS[] = null;
    protected static Color ACTIVE_COLORS[] = null;
    protected static Color INACTIVE_COLORS[] = null;
    protected static Color ROLLOVER_COLORS[] = null;
    protected static Color SELECTED_COLORS[] = null;
    protected static Color PRESSED_COLORS[] = null;
    protected static Color DISABLED_COLORS[] = null;
    protected static Color WINDOW_TITLE_COLORS[] = null;
    protected static Color WINDOW_INACTIVE_TITLE_COLORS[] = null;
    protected static Color TOOLBAR_COLORS[] = null;
    protected static Color MENUBAR_COLORS[] = null;
    protected static Color BUTTON_COLORS[] = null;
    protected static Color TAB_COLORS[] = null;
    protected static Color COL_HEADER_COLORS[] = null;
    protected static Color TRACK_COLORS[] = null;
    protected static Color THUMB_COLORS[] = null;
    protected static Color SLIDER_COLORS[] = null;
    protected static Color PROGRESSBAR_COLORS[] = null;

    public BaseTheme() {
        super();
        internalName = "Default";
        setUp();
        setupColorArrs();
    }

    public String getName() {
        return getInternalName();
    }

    public static String getInternalName() {
        return internalName;
    }

    public static void setInternalName(String name) {
        internalName = name;
    }

    public String getPropertyFileName() {
        return "JTattooTheme.properties";
    }

    public static void setUp() {
        windowDecoration = true;
        dynamicLayout = true;
        textAntiAliasing = false;
        textAntiAliasingMode = TEXT_ANTIALIAS_HRGB;
        backgroundPattern = true;
        menuOpaque = true;
        menuAlpha = 0.9f;
        logoString = "JTattoo";

        controlFont = null;
        systemFont = null;
        userFont = null;
        smallFont = null;
        menuFont = null;
        windowTitleFont = null;

        foregroundColor = black;
        disabledForegroundColor = gray;
        disabledBackgroundColor = superLightGray;
        backgroundColor = extraLightGray;
        backgroundColorLight = white;
        backgroundColorDark = extraLightGray;
        inputBackgroundColor = white;
        inputForegroundColor = black;
        selectionForegroundColor = black;
        selectionBackgroundColor = lightGray;
        focusColor = orange;
        focusCellColor = orange;
        frameColor = darkGray;
        gridColor = gray;

        rolloverColor = extraLightGray;
        rolloverColorLight = white;
        rolloverColorDark = extraLightGray;

        buttonForegroundColor = black;
        buttonBackgroundColor = lightGray;
        buttonColorLight = white;
        buttonColorDark = lightGray;

        controlForegroundColor = black;
        controlBackgroundColor = lightGray;
        controlHighlightColor = white;
        controlShadowColor = lightGray;
        controlDarkShadowColor = darkGray;
        controlColorLight = white;
        controlColorDark = lightGray;

        windowTitleForegroundColor = black;
        windowTitleBackgroundColor = blue;
        windowTitleColorLight = extraLightGray;
        windowTitleColorDark = lightGray;
        windowBorderColor = lightGray;
        windowInactiveTitleForegroundColor = black;
        windowInactiveTitleBackgroundColor = extraLightGray;
        windowInactiveTitleColorLight = white;
        windowInactiveTitleColorDark = extraLightGray;
        windowInactiveBorderColor = extraLightGray;

        menuForegroundColor = black;
        menuBackgroundColor = extraLightGray;
        menuSelectionForegroundColor = black;
        menuSelectionBackgroundColor = lightGray;
        menuColorLight = extraLightGray;
        menuColorDark = lightGray;

        toolbarForegroundColor = black;
        toolbarBackgroundColor = lightGray;
        toolbarColorLight = white;
        toolbarColorDark = lightGray;

        desktopColor = darkBlue;
        tooltipForegroundColor = black;
        tooltipBackgroundColor = yellow;
    }

    public static void setupColorArrs() {
        DEFAULT_COLORS = ColorHelper.createColorArr(controlColorLight, controlColorDark, 20);
        HIDEFAULT_COLORS = ColorHelper.createColorArr(ColorHelper.brighter(controlColorLight, 40), ColorHelper.brighter(controlColorDark, 40), 20);
        ACTIVE_COLORS = DEFAULT_COLORS;
        INACTIVE_COLORS = HIDEFAULT_COLORS;
        ROLLOVER_COLORS = ColorHelper.createColorArr(rolloverColorLight, rolloverColorDark, 20);
        SELECTED_COLORS = DEFAULT_COLORS;
        PRESSED_COLORS = DEFAULT_COLORS;
        DISABLED_COLORS = HIDEFAULT_COLORS;
        WINDOW_TITLE_COLORS = ColorHelper.createColorArr(windowTitleColorLight, windowTitleColorDark, 20);
        WINDOW_INACTIVE_TITLE_COLORS = ColorHelper.createColorArr(windowInactiveTitleColorLight, windowInactiveTitleColorDark, 20);
        TOOLBAR_COLORS = ColorHelper.createColorArr(toolbarColorLight, toolbarColorDark, 20);
        MENUBAR_COLORS = ColorHelper.createColorArr(menuColorLight, menuColorDark, 20);
        BUTTON_COLORS = ColorHelper.createColorArr(buttonColorLight, buttonColorDark, 20);
        TAB_COLORS = DEFAULT_COLORS;
        COL_HEADER_COLORS = DEFAULT_COLORS;
        TRACK_COLORS = ColorHelper.createColorArr(new Color(220, 220, 220), Color.white, 20);
        THUMB_COLORS = DEFAULT_COLORS;
        SLIDER_COLORS = DEFAULT_COLORS;
        PROGRESSBAR_COLORS = DEFAULT_COLORS;
    }

    public static void setProperties(Properties props) {
        if (props != null) {
            if (props.getProperty("windowDecoration") != null) {
                windowDecoration = props.getProperty("windowDecoration").trim().equalsIgnoreCase("on");
            }
            if (props.getProperty("dynamicLayout") != null) {
                dynamicLayout = props.getProperty("dynamicLayout").trim().equalsIgnoreCase("on");
            }
            if (props.getProperty("textAntiAliasing") != null) {
                textAntiAliasing = props.getProperty("textAntiAliasing").trim().equalsIgnoreCase("on");
            }
            if (props.getProperty("textAntiAliasingMode") != null) {
                String mode = props.getProperty("textAntiAliasingMode");
                if (mode.equalsIgnoreCase("default")) {
                    textAntiAliasingMode = TEXT_ANTIALIAS_DEFAULT;
                }
                if (mode.equalsIgnoreCase("gray")) {
                    textAntiAliasingMode = TEXT_ANTIALIAS_GRAY;
                }
                if (mode.equalsIgnoreCase("hrgb")) {
                    textAntiAliasingMode = TEXT_ANTIALIAS_HRGB;
                }
                if (mode.equalsIgnoreCase("hbgr")) {
                    textAntiAliasingMode = TEXT_ANTIALIAS_HBGR;
                }
                if (mode.equalsIgnoreCase("vrgb")) {
                    textAntiAliasingMode = TEXT_ANTIALIAS_VRGB;
                }
                if (mode.equalsIgnoreCase("vbgr")) {
                    textAntiAliasingMode = TEXT_ANTIALIAS_VBGR;
                }
            }
            if (props.getProperty("backgroundPattern") != null) {
                backgroundPattern = props.getProperty("backgroundPattern").trim().equalsIgnoreCase("on");
            }
            if (props.getProperty("menuOpaque") != null) {
                menuOpaque = props.getProperty("menuOpaque").trim().equalsIgnoreCase("on");
            }
            if (props.getProperty("logoString") != null) {
                logoString = props.getProperty("logoString").trim();
            }
            if (props.getProperty("controlTextFont") != null) {
                controlFont = createFont(props.getProperty("controlTextFont"));
            }
            if (props.getProperty("systemTextFont") != null) {
                systemFont = createFont(props.getProperty("systemTextFont"));
            }
            if (props.getProperty("userTextFont") != null) {
                userFont = createFont(props.getProperty("userTextFont"));
            }
            if (props.getProperty("menuTextFont") != null) {
                menuFont = createFont(props.getProperty("menuTextFont"));
            }
            if (props.getProperty("windowTitleFont") != null) {
                windowTitleFont = createFont(props.getProperty("windowTitleFont"));
            }
            if (props.getProperty("subTextFont") != null) {
                smallFont = createFont(props.getProperty("subTextFont"));
            }

            if (props.getProperty("foregroundColor") != null) {
                foregroundColor = createColor(props.getProperty("foregroundColor"), foregroundColor);
            }
            if (props.getProperty("disabledForegroundColor") != null) {
                disabledForegroundColor = createColor(props.getProperty("disabledForegroundColor"), disabledForegroundColor);
            }
            if (props.getProperty("disabledBackgroundColor") != null) {
                disabledBackgroundColor = createColor(props.getProperty("disabledBackgroundColor"), disabledBackgroundColor);
            }
            if (props.getProperty("backgroundColor") != null) {
                backgroundColor = createColor(props.getProperty("backgroundColor"), backgroundColor);
            }
            if (props.getProperty("backgroundColorLight") != null) {
                backgroundColorLight = createColor(props.getProperty("backgroundColorLight"), backgroundColorLight);
            }
            if (props.getProperty("backgroundColorDark") != null) {
                backgroundColorDark = createColor(props.getProperty("backgroundColorDark"), backgroundColorDark);
            }
            if (props.getProperty("inputForegroundColor") != null) {
                inputForegroundColor = createColor(props.getProperty("inputForegroundColor"), inputForegroundColor);
            }
            if (props.getProperty("inputBackgroundColor") != null) {
                inputBackgroundColor = createColor(props.getProperty("inputBackgroundColor"), inputBackgroundColor);
            }
            if (props.getProperty("selectionForegroundColor") != null) {
                selectionForegroundColor = createColor(props.getProperty("selectionForegroundColor"), selectionForegroundColor);
            }
            if (props.getProperty("selectionBackgroundColor") != null) {
                selectionBackgroundColor = createColor(props.getProperty("selectionBackgroundColor"), selectionBackgroundColor);
            }
            if (props.getProperty("frameColor") != null) {
                frameColor = createColor(props.getProperty("frameColor"), frameColor);
            }
            if (props.getProperty("gridColor") != null) {
                gridColor = createColor(props.getProperty("gridColor"), gridColor);
            }
            if (props.getProperty("focusColor") != null) {
                focusColor = createColor(props.getProperty("focusColor"), focusColor);
            }
            if (props.getProperty("focusCellColor") != null) {
                focusCellColor = createColor(props.getProperty("focusCellColor"), focusCellColor);
            }

            if (props.getProperty("rolloverColor") != null) {
                rolloverColor = createColor(props.getProperty("rolloverColor"), rolloverColor);
            }
            if (props.getProperty("rolloverColorLight") != null) {
                rolloverColorLight = createColor(props.getProperty("rolloverColorLight"), rolloverColorLight);
            }
            if (props.getProperty("rolloverColorDark") != null) {
                rolloverColorDark = createColor(props.getProperty("rolloverColorDark"), rolloverColorDark);
            }

            if (props.getProperty("buttonForegroundColor") != null) {
                buttonForegroundColor = createColor(props.getProperty("buttonForegroundColor"), buttonForegroundColor);
            }
            if (props.getProperty("buttonBackgroundColor") != null) {
                buttonBackgroundColor = createColor(props.getProperty("buttonBackgroundColor"), buttonBackgroundColor);
            }
            if (props.getProperty("buttonColorLight") != null) {
                buttonColorLight = createColor(props.getProperty("buttonColorLight"), buttonColorLight);
            }
            if (props.getProperty("buttonColorDark") != null) {
                buttonColorDark = createColor(props.getProperty("buttonColorDark"), buttonColorDark);
            }

            if (props.getProperty("controlForegroundColor") != null) {
                controlForegroundColor = createColor(props.getProperty("controlForegroundColor"), controlForegroundColor);
            }
            if (props.getProperty("controlBackgroundColor") != null) {
                controlBackgroundColor = createColor(props.getProperty("controlBackgroundColor"), controlBackgroundColor);
            }
            if (props.getProperty("controlColorLight") != null) {
                controlColorLight = createColor(props.getProperty("controlColorLight"), controlColorLight);
            }
            if (props.getProperty("controlColorDark") != null) {
                controlColorDark = createColor(props.getProperty("controlColorDark"), controlColorDark);
            }

            if (props.getProperty("windowTitleForegroundColor") != null) {
                windowTitleForegroundColor = createColor(props.getProperty("windowTitleForegroundColor"), windowTitleForegroundColor);
            }
            if (props.getProperty("windowTitleBackgroundColor") != null) {
                windowTitleBackgroundColor = createColor(props.getProperty("windowTitleBackgroundColor"), windowTitleBackgroundColor);
            }
            if (props.getProperty("windowTitleColorLight") != null) {
                windowTitleColorLight = createColor(props.getProperty("windowTitleColorLight"), windowTitleColorLight);
            }
            if (props.getProperty("windowTitleColorDark") != null) {
                windowTitleColorDark = createColor(props.getProperty("windowTitleColorDark"), windowTitleColorDark);
            }
            if (props.getProperty("windowBorderColor") != null) {
                windowBorderColor = createColor(props.getProperty("windowBorderColor"), windowBorderColor);
            }

            if (props.getProperty("windowInactiveTitleForegroundColor") != null) {
                windowInactiveTitleForegroundColor = createColor(props.getProperty("windowInactiveTitleForegroundColor"), windowInactiveTitleForegroundColor);
            }
            if (props.getProperty("windowTitleBackgroundColor") != null) {
                windowInactiveTitleBackgroundColor = createColor(props.getProperty("windowInactiveTitleBackgroundColor"), windowInactiveTitleBackgroundColor);
            }
            if (props.getProperty("windowInactiveTitleColorLight") != null) {
                windowInactiveTitleColorLight = createColor(props.getProperty("windowInactiveTitleColorLight"), windowInactiveTitleColorLight);
            }
            if (props.getProperty("windowInactiveTitleColorDark") != null) {
                windowInactiveTitleColorDark = createColor(props.getProperty("windowInactiveTitleColorDark"), windowInactiveTitleColorDark);
            }
            if (props.getProperty("windowInactiveBorderColor") != null) {
                windowInactiveBorderColor = createColor(props.getProperty("windowInactiveBorderColor"), windowInactiveBorderColor);
            }

            if (props.getProperty("menuForegroundColor") != null) {
                menuForegroundColor = createColor(props.getProperty("menuForegroundColor"), menuForegroundColor);
            }
            if (props.getProperty("menuBackgroundColor") != null) {
                menuBackgroundColor = createColor(props.getProperty("menuBackgroundColor"), menuBackgroundColor);
            }
            if (props.getProperty("menuSelectionForegroundColor") != null) {
                menuSelectionForegroundColor = createColor(props.getProperty("menuSelectionForegroundColor"), menuSelectionForegroundColor);
            }
            if (props.getProperty("menuSelectionBackgroundColor") != null) {
                menuSelectionBackgroundColor = createColor(props.getProperty("menuSelectionBackgroundColor"), menuSelectionBackgroundColor);
            }
            if (props.getProperty("menuColorLight") != null) {
                menuColorLight = createColor(props.getProperty("menuColorLight"), menuColorLight);
            }
            if (props.getProperty("menuColorDark") != null) {
                menuColorDark = createColor(props.getProperty("menuColorDark"), menuColorDark);
            }

            if (props.getProperty("toolbarForegroundColor") != null) {
                toolbarForegroundColor = createColor(props.getProperty("toolbarForegroundColor"), toolbarForegroundColor);
            }
            if (props.getProperty("toolbarBackgroundColor") != null) {
                toolbarBackgroundColor = createColor(props.getProperty("toolbarBackgroundColor"), toolbarBackgroundColor);
            }
            if (props.getProperty("toolbarColorLight") != null) {
                toolbarColorLight = createColor(props.getProperty("toolbarColorLight"), toolbarColorLight);
            }
            if (props.getProperty("toolbarColorDark") != null) {
                toolbarColorDark = createColor(props.getProperty("toolbarColorDark"), toolbarColorDark);
            }

            if (props.getProperty("desktopColor") != null) {
                desktopColor = createColor(props.getProperty("desktopColor"), desktopColor);
            }
            if (props.getProperty("tooltipForegroundColor") != null) {
                tooltipForegroundColor = createColor(props.getProperty("tooltipForegroundColor"), tooltipForegroundColor);
            }
            if (props.getProperty("tooltipBackgroundColor") != null) {
                tooltipBackgroundColor = createColor(props.getProperty("tooltipBackgroundColor"), tooltipBackgroundColor);
            }
        }
    }

    public void loadProperties() {
        FileInputStream in = null;
        try {
            String fileName = System.getProperty("user.home") + "/.jtattoo/" + getPropertyFileName();
            Properties props = new Properties();
            in = new FileInputStream(fileName);
            props.load(in);
            setProperties(props);
        } catch (Exception ex) {
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
            }
        }
    }

    protected static FontUIResource createFont(String fontProp) {
        if ((fontProp != null) && (fontProp.trim().length() > 5)) {
            return new FontUIResource(Font.decode(fontProp));
        }
        return null;
    }

    protected static ColorUIResource createColor(String colorProp, ColorUIResource color) {
        if ((colorProp != null) && (colorProp.trim().length() >= 5)) {
            colorProp = colorProp.trim();
            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            try {
                int p1 = 0;
                int p2 = colorProp.indexOf(' ');
                if (p2 > 0) {
                    r = Integer.parseInt(colorProp.substring(p1, p2));
                }
                p1 = p2 + 1;
                p2 = colorProp.indexOf(' ', p1);
                if (p2 > 0) {
                    g = Integer.parseInt(colorProp.substring(p1, p2));
                }
                b = Integer.parseInt(colorProp.substring(p2 + 1));
                return new ColorUIResource(r, g, b);
            } catch (Exception ex) {
                System.out.println("Exception while parsing color: " + colorProp);
            }
        }
        return color;
    }

    public FontUIResource getControlTextFont() {
        if (controlFont == null) {
            if (JTattooUtilities.isLinux() && JTattooUtilities.isHiresScreen()) {
                controlFont = new FontUIResource(DIALOG, Font.BOLD, 14);
            } else {
                controlFont = new FontUIResource(DIALOG, Font.PLAIN, 12);
            }
        }
        return controlFont;
    }

    public FontUIResource getSystemTextFont() {
        if (systemFont == null) {
            if (JTattooUtilities.isLinux() && JTattooUtilities.isHiresScreen()) {
                systemFont = new FontUIResource(DIALOG, Font.BOLD, 14);
            } else {
                systemFont = new FontUIResource(DIALOG, Font.PLAIN, 12);
            }
        }
        return systemFont;
    }

    public FontUIResource getUserTextFont() {
        if (userFont == null) {
            if (JTattooUtilities.isLinux() && JTattooUtilities.isHiresScreen()) {
                userFont = new FontUIResource(DIALOG, Font.BOLD, 14);
            } else {
                userFont = new FontUIResource(DIALOG, Font.PLAIN, 12);
            }
        }
        return userFont;
    }

    public FontUIResource getMenuTextFont() {
        if (menuFont == null) {
            if (JTattooUtilities.isLinux() && JTattooUtilities.isHiresScreen()) {
                menuFont = new FontUIResource(DIALOG, Font.BOLD, 14);
            } else {
                menuFont = new FontUIResource(DIALOG, Font.PLAIN, 12);
            }
        }
        return menuFont;
    }

    public FontUIResource getWindowTitleFont() {
        if (windowTitleFont == null) {
            if (JTattooUtilities.isLinux() && JTattooUtilities.isHiresScreen()) {
                windowTitleFont = new FontUIResource(DIALOG, Font.BOLD, 14);
            } else {
                windowTitleFont = new FontUIResource(DIALOG, Font.BOLD, 12);
            }
        }
        return windowTitleFont;
    }

    public FontUIResource getSubTextFont() {
        if (smallFont == null) {
            if (JTattooUtilities.isLinux() && JTattooUtilities.isHiresScreen()) {
                smallFont = new FontUIResource(DIALOG, Font.BOLD, 12);
            } else {
                smallFont = new FontUIResource(DIALOG, Font.PLAIN, 10);
            }
        }
        return smallFont;
    }

    //-----------------------------------------------------------------------------------
    protected ColorUIResource getPrimary1() {
        return foregroundColor;
    }

    protected ColorUIResource getPrimary2() {
        return desktopColor;
    }

    protected ColorUIResource getPrimary3() {
        return selectionBackgroundColor;
    }

    protected ColorUIResource getSecondary1() {
        return frameColor;
    }

    protected ColorUIResource getSecondary2() {
        return controlBackgroundColor;
    }

    protected ColorUIResource getSecondary3() {
        return backgroundColor;
    }

    public ColorUIResource getControl() {
        return controlBackgroundColor;
    }

    public ColorUIResource getControlShadow() {
        return controlShadowColor;
    }

    public ColorUIResource getControlDarkShadow() {
        return controlDarkShadowColor;
    }

    public ColorUIResource getControlInfo() {
        return controlForegroundColor;
    }

    public ColorUIResource getControlHighlight() {
        return controlHighlightColor;
    }

    public ColorUIResource getControlDisabled() {
        return controlShadowColor;
    }

    /*
    public ColorUIResource getControl()
    { return red; }
    public ColorUIResource getControlShadow()
    { return blue; }
    public ColorUIResource getControlDarkShadow()
    { return green; }
    public ColorUIResource getControlInfo()
    { return yellow; }
    public ColorUIResource getControlHighlight()
    { return orange; }
    public ColorUIResource getControlDisabled()
    { return cyan;}
     */
    public ColorUIResource getPrimaryControl() {
        return extraLightGray;
    }

    public ColorUIResource getPrimaryControlShadow() {
        return lightGray;
    }

    public ColorUIResource getPrimaryControlDarkShadow() {
        return gray;
    }

    public ColorUIResource getPrimaryControlInfo() {
        return darkGray;
    }

    public ColorUIResource getPrimaryControlHighlight() {
        return white;
    }

    public ColorUIResource getControlTextColor() {
        return controlForegroundColor;
    }

    public ColorUIResource getSystemTextColor() {
        return foregroundColor;
    }

    public String getLogoString() {
        if (logoString != null) {
            if (logoString.trim().length() == 0) {
                return null;
            }
        }
        return logoString;
    }

    public boolean isWindowDecorationOn() {
        return windowDecoration;
    }

    public boolean isDynamicLayout() {
        return dynamicLayout;
    }

    public boolean isTextAntiAliasingOn() {
        if (JTattooUtilities.getJavaVersion() < 1.4) {
            return false;
        }
        return textAntiAliasing;
    }

    public int getTextAntiAliasingMode() {
        return textAntiAliasingMode;
    }

    public Object getTextAntiAliasingHint() {
        if (isTextAntiAliasingOn()) {
            if (JTattooUtilities.getJavaVersion() >= 1.6) {
                switch (textAntiAliasingMode) {
                    case TEXT_ANTIALIAS_DEFAULT:
                        return RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT;
                    case TEXT_ANTIALIAS_HRGB:
                        return RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB;
                    case TEXT_ANTIALIAS_HBGR:
                        return RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR;
                    case TEXT_ANTIALIAS_VRGB:
                        return RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB;
                    case TEXT_ANTIALIAS_VBGR:
                        return RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR;
                    default:
                        return RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
                }
            }
            return RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
        }
        return RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
    }

    public boolean isBackgroundPatternOn() {
        return backgroundPattern;
    }

    public boolean isMenuOpaque() {
        return menuOpaque;
    }

    public float getMenuAlpha() {
        return menuAlpha;
    }

    public ColorUIResource getForegroundColor() {
        return foregroundColor;
    }

    public ColorUIResource getDisabledForegroundColor() {
        return disabledForegroundColor;
    }

    public ColorUIResource getBackgroundColor() {
        return backgroundColor;
    }

    public ColorUIResource getDisabledBackgroundColor() {
        return disabledBackgroundColor;
    }

    public ColorUIResource getBackgroundColorLight() {
        return backgroundColorLight;
    }

    public ColorUIResource getBackgroundColorDark() {
        return backgroundColorDark;
    }

    public ColorUIResource getInputForegroundColor() {
        return inputForegroundColor;
    }

    public ColorUIResource getInputBackgroundColor() {
        return inputBackgroundColor;
    }

    public ColorUIResource getSelectionForegroundColor() {
        return selectionForegroundColor;
    }

    public ColorUIResource getSelectionBackgroundColor() {
        return selectionBackgroundColor;
    }

    public ColorUIResource getFrameColor() {
        return frameColor;
    }

    public ColorUIResource getGridColor() {
        return gridColor;
    }

    public ColorUIResource getFocusColor() {
        return focusColor;
    }

    public ColorUIResource getFocusCellColor() {
        return focusCellColor;
    }

    public ColorUIResource getRolloverColor() {
        return rolloverColor;
    }

    public ColorUIResource getRolloverColorLight() {
        return rolloverColorLight;
    }

    public ColorUIResource getRolloverColorDark() {
        return rolloverColorDark;
    }

    public ColorUIResource getButtonForegroundColor() {
        return buttonForegroundColor;
    }

    public ColorUIResource getButtonBackgroundColor() {
        return buttonBackgroundColor;
    }

    public ColorUIResource getButtonColorLight() {
        return buttonColorLight;
    }

    public ColorUIResource getButtonColorDark() {
        return buttonColorDark;
    }

    public ColorUIResource getControlForegroundColor() {
        return controlForegroundColor;
    }

    public ColorUIResource getControlBackgroundColor() {
        return controlBackgroundColor;
    }

    public ColorUIResource getControlHighlightColor() {
        return controlHighlightColor;
    }

    public ColorUIResource getControlShadowColor() {
        return controlShadowColor;
    }

    public ColorUIResource getControlDarkShadowColor() {
        return controlDarkShadowColor;
    }

    public ColorUIResource getControlColorLight() {
        return controlColorLight;
    }

    public ColorUIResource getControlColorDark() {
        return controlColorDark;
    }

    public ColorUIResource getWindowTitleForegroundColor() {
        return windowTitleForegroundColor;
    }

    public ColorUIResource getWindowTitleBackgroundColor() {
        return windowTitleBackgroundColor;
    }

    public ColorUIResource getWindowTitleColorLight() {
        return windowTitleColorLight;
    }

    public ColorUIResource getWindowTitleColorDark() {
        return windowTitleColorDark;
    }

    public ColorUIResource getWindowBorderColor() {
        return windowBorderColor;
    }

    public ColorUIResource getWindowInactiveTitleForegroundColor() {
        return windowInactiveTitleForegroundColor;
    }

    public ColorUIResource getWindowInactiveTitleBackgroundColor() {
        return windowInactiveTitleBackgroundColor;
    }

    public ColorUIResource getWindowInactiveTitleColorLight() {
        return windowInactiveTitleColorLight;
    }

    public ColorUIResource getWindowInactiveTitleColorDark() {
        return windowInactiveTitleColorDark;
    }

    public ColorUIResource getWindowInactiveBorderColor() {
        return windowInactiveBorderColor;
    }

    public ColorUIResource getMenuForegroundColor() {
        return menuForegroundColor;
    }

    public ColorUIResource getMenuBackgroundColor() {
        return menuBackgroundColor;
    }

    public ColorUIResource getMenuSelectionForegroundColor() {
        return menuSelectionForegroundColor;
    }

    public ColorUIResource getMenuSelectionBackgroundColor() {
        return menuSelectionBackgroundColor;
    }

    public ColorUIResource getMenuColorLight() {
        return menuColorLight;
    }

    public ColorUIResource getMenuColorDark() {
        return menuColorDark;
    }

    public ColorUIResource getToolbarForegroundColor() {
        return toolbarForegroundColor;
    }

    public ColorUIResource getToolbarBackgroundColor() {
        return toolbarBackgroundColor;
    }

    public ColorUIResource getToolbarColorLight() {
        return toolbarColorLight;
    }

    public ColorUIResource getToolbarColorDark() {
        return toolbarColorDark;
    }

    public ColorUIResource getDesktopColor() {
        return desktopColor;
    }

    public ColorUIResource getTooltipForegroundColor() {
        return tooltipForegroundColor;
    }

    public ColorUIResource getTooltipBackgroundColor() {
        return tooltipBackgroundColor;
    }

    public Color[] getDefaultColors() {
        return DEFAULT_COLORS;
    }

    public Color[] getHiDefaultColors() {
        return HIDEFAULT_COLORS;
    }

    public Color[] getActiveColors() {
        return ACTIVE_COLORS;
    }

    public Color[] getInActiveColors() {
        return INACTIVE_COLORS;
    }

    public Color[] getRolloverColors() {
        return ROLLOVER_COLORS;
    }

    public Color[] getSelectedColors() {
        return SELECTED_COLORS;
    }

    public Color[] getPressedColors() {
        return PRESSED_COLORS;
    }

    public Color[] getDisabledColors() {
        return DISABLED_COLORS;
    }

    public Color[] getWindowTitleColors() {
        return WINDOW_TITLE_COLORS;
    }

    public Color[] getWindowInactiveTitleColors() {
        return WINDOW_INACTIVE_TITLE_COLORS;
    }

    public Color[] getToolBarColors() {
        return TOOLBAR_COLORS;
    }

    public Color[] getMenuBarColors() {
        return MENUBAR_COLORS;
    }

    public Color[] getButtonColors() {
        return BUTTON_COLORS;
    }

    public Color[] getTabColors() {
        return TAB_COLORS;
    }

    public Color[] getColHeaderColors() {
        return COL_HEADER_COLORS;
    }

    public Color[] getTrackColors() {
        return TRACK_COLORS;
    }

    public Color[] getThumbColors() {
        return THUMB_COLORS;
    }

    public Color[] getSliderColors() {
        return SLIDER_COLORS;
    }

    public Color[] getProgressBarColors() {
        return PROGRESSBAR_COLORS;
    }
}
