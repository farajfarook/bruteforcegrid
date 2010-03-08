/*
 * Copyright 2005 MH-Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */
package jtattoo.plaf.acryl;

import java.util.*;
import javax.swing.*;

import jtattoo.plaf.*;

/**
 * @author Michael Hagen
 */
public class AcrylLookAndFeel extends AbstractLookAndFeel {

    private static AcrylDefaultTheme myTheme = null;
    private static ArrayList themesList = new ArrayList();
    private static HashMap themesMap = new HashMap();
    private static Properties defaultProps = new Properties();
    private static Properties smallFontProps = new Properties();
    private static Properties largeFontProps = new Properties();
    private static Properties giantFontProps = new Properties();
    private static Properties greenProps = new Properties();
    private static Properties greenSmallFontProps = new Properties();
    private static Properties greenLargeFontProps = new Properties();
    private static Properties greenGiantFontProps = new Properties();
    private static Properties lemmonProps = new Properties();
    private static Properties lemmonSmallFontProps = new Properties();
    private static Properties lemmonLargeFontProps = new Properties();
    private static Properties lemmonGiantFontProps = new Properties();
    private static Properties redProps = new Properties();
    private static Properties redSmallFontProps = new Properties();
    private static Properties redLargeFontProps = new Properties();
    private static Properties redGiantFontProps = new Properties();


    static {
        smallFontProps.setProperty("controlTextFont", "Dialog 10");
        smallFontProps.setProperty("systemTextFont", "Dialog 10");
        smallFontProps.setProperty("userTextFont", "Dialog 10");
        smallFontProps.setProperty("menuTextFont", "Dialog 10");
        smallFontProps.setProperty("windowTitleFont", "Dialog bold 10");
        smallFontProps.setProperty("subTextFont", "Dialog 8");

        largeFontProps.setProperty("controlTextFont", "Dialog 14");
        largeFontProps.setProperty("systemTextFont", "Dialog 14");
        largeFontProps.setProperty("userTextFont", "Dialog 14");
        largeFontProps.setProperty("menuTextFont", "Dialog 14");
        largeFontProps.setProperty("windowTitleFont", "Dialog bold 14");
        largeFontProps.setProperty("subTextFont", "Dialog 12");

        giantFontProps.setProperty("controlTextFont", "Dialog 18");
        giantFontProps.setProperty("systemTextFont", "Dialog 18");
        giantFontProps.setProperty("userTextFont", "Dialog 18");
        giantFontProps.setProperty("menuTextFont", "Dialog 18");
        giantFontProps.setProperty("windowTitleFont", "Dialog 18");
        giantFontProps.setProperty("subTextFont", "Dialog 16");

        greenProps.setProperty("backgroundColor", "232 229 222");
        greenProps.setProperty("frameColor", "24 58 39");
        greenProps.setProperty("selectionBackgroundColor", "54 126 85");
        greenProps.setProperty("menuSelectionBackgroundColor", "54 126 85");
        greenProps.setProperty("controlColorLight", "64 149 100");
        greenProps.setProperty("controlColorDark", "40 94 63");
        greenProps.setProperty("rolloverColor", "134 94 0");
        greenProps.setProperty("rolloverColorLight", "255 213 113");
        greenProps.setProperty("rolloverColorDark", "240 168 0");
        greenProps.setProperty("windowTitleBackgroundColor", "64 149 100");
        greenProps.setProperty("windowTitleColorLight", "64 149 100");
        greenProps.setProperty("windowTitleColorDark", "40 94 63");
        greenProps.setProperty("windowBorderColor", "40 94 63");
        greenProps.setProperty("windowInactiveTitleBackgroundColor", "232 229 222");
        greenProps.setProperty("windowInactiveTitleColorLight", "238 236 232");
        greenProps.setProperty("windowInactiveTitleColorDark", "232 229 222");
        greenProps.setProperty("windowInactiveBorderColor", "180 169 146");
        greenProps.setProperty("menuBackgroundColor", "232 229 222");
        greenProps.setProperty("menuColorLight", "238 236 232");
        greenProps.setProperty("menuColorDark", "232 229 222");
        greenProps.setProperty("toolbarBackgroundColor", "232 229 222");
        greenProps.setProperty("toolbarColorLight", "238 236 232");
        greenProps.setProperty("toolbarColorDark", "232 229 222");
        greenProps.setProperty("desktopColor", "244 242 232");

        lemmonProps.setProperty("backgroundColor", "240 243 242");
        lemmonProps.setProperty("frameColor", "120 159 17");
        lemmonProps.setProperty("selectionForegroundColor", "0 0 0");
        lemmonProps.setProperty("selectionBackgroundColor", "175 232 28");
        lemmonProps.setProperty("rolloverColor", "112 148 16");
        lemmonProps.setProperty("rolloverColorLight", "243 254 180");
        lemmonProps.setProperty("rolloverColorDark", "231 253 104");
        lemmonProps.setProperty("windowTitleForegroundColor", "243 254 180");
        lemmonProps.setProperty("windowTitleBackgroundColor", "164 217 23");
        lemmonProps.setProperty("windowTitleColorLight", "164 217 23");
        lemmonProps.setProperty("windowTitleColorDark", "140 186 20");
        lemmonProps.setProperty("windowBorderColor", "106 140 15");
        lemmonProps.setProperty("controlColorLight", "207 245 35");
        lemmonProps.setProperty("controlColorDark", "155 211 18");
        lemmonProps.setProperty("menuBackgroundColor", "240 243 242");
        lemmonProps.setProperty("menuSelectionForegroundColor", "0 0 0");
        lemmonProps.setProperty("menuSelectionBackgroundColor", "175 232 28");
        lemmonProps.setProperty("menuColorLight", "244 247 245");
        lemmonProps.setProperty("menuColorDark", "232 236 235");
        lemmonProps.setProperty("toolbarBackgroundColor", "240 243 242");
        lemmonProps.setProperty("toolbarColorLight", "244 247 245");
        lemmonProps.setProperty("toolbarColorDark", "232 236 235");

        redProps.setProperty("backgroundColor", "244 244 244");
        redProps.setProperty("frameColor", "64 32 32");
        redProps.setProperty("selectionForegroundColor", "255 255 255");
        redProps.setProperty("selectionBackgroundColor", "220 0 0");
        redProps.setProperty("rolloverColor", "160 160 80");
        redProps.setProperty("rolloverColorLight", "248 248 180");
        redProps.setProperty("rolloverColorDark", "200 200 120");
        redProps.setProperty("windowTitleForegroundColor", "255 255 255");
        redProps.setProperty("windowTitleBackgroundColor", "64 149 100");
        redProps.setProperty("windowTitleColorLight", "255 24 24");
        redProps.setProperty("windowTitleColorDark", "180 0 0");
        redProps.setProperty("windowBorderColor", "180 0 0");
        redProps.setProperty("controlColorLight", "255 24 24");
        redProps.setProperty("controlColorDark", "180 0 0");
        redProps.setProperty("menuBackgroundColor", "248 248 248");
        redProps.setProperty("menuSelectionForegroundColor", "255 255 255");
        redProps.setProperty("menuSelectionBackgroundColor", "220 0 0");
        redProps.setProperty("menuColorLight", "248 248 248");
        redProps.setProperty("menuColorDark", "236 236 236");
        redProps.setProperty("toolbarBackgroundColor", "248 248 248");
        redProps.setProperty("toolbarColorLight", "248 248 248");
        redProps.setProperty("toolbarColorDark", "236 236 236");

        String key = null;
        String value = null;
        Iterator iter = smallFontProps.keySet().iterator();
        while (iter.hasNext()) {
            key = (String) iter.next();
            value = smallFontProps.getProperty(key);
            greenSmallFontProps.setProperty(key, value);
            lemmonSmallFontProps.setProperty(key, value);
            redSmallFontProps.setProperty(key, value);
        }
        iter = largeFontProps.keySet().iterator();
        while (iter.hasNext()) {
            key = (String) iter.next();
            value = largeFontProps.getProperty(key);
            greenLargeFontProps.setProperty(key, value);
            lemmonLargeFontProps.setProperty(key, value);
            redLargeFontProps.setProperty(key, value);
        }
        iter = giantFontProps.keySet().iterator();
        while (iter.hasNext()) {
            key = (String) iter.next();
            value = giantFontProps.getProperty(key);
            greenGiantFontProps.setProperty(key, value);
            lemmonGiantFontProps.setProperty(key, value);
            redGiantFontProps.setProperty(key, value);
        }

        iter = greenProps.keySet().iterator();
        while (iter.hasNext()) {
            key = (String) iter.next();
            value = greenProps.getProperty(key);
            greenSmallFontProps.setProperty(key, value);
            greenLargeFontProps.setProperty(key, value);
            greenGiantFontProps.setProperty(key, value);
        }
        iter = lemmonProps.keySet().iterator();
        while (iter.hasNext()) {
            key = (String) iter.next();
            value = lemmonProps.getProperty(key);
            lemmonSmallFontProps.setProperty(key, value);
            lemmonLargeFontProps.setProperty(key, value);
            lemmonGiantFontProps.setProperty(key, value);
        }
        iter = redProps.keySet().iterator();
        while (iter.hasNext()) {
            key = (String) iter.next();
            value = redProps.getProperty(key);
            redSmallFontProps.setProperty(key, value);
            redLargeFontProps.setProperty(key, value);
            redGiantFontProps.setProperty(key, value);
        }

        themesList.add("Default");
        themesList.add("Small-Font");
        themesList.add("Large-Font");
        themesList.add("Giant-Font");

        themesList.add("Green");
        themesList.add("Green-Small-Font");
        themesList.add("Green-Large-Font");
        themesList.add("Green-Giant-Font");

        themesList.add("Lemmon");
        themesList.add("Lemmon-Small-Font");
        themesList.add("Lemmon-Large-Font");
        themesList.add("Lemmon-Giant-Font");

        themesList.add("Red");
        themesList.add("Red-Small-Font");
        themesList.add("Red-Large-Font");
        themesList.add("Red-Giant-Font");

        themesMap.put("Default", defaultProps);
        themesMap.put("Small-Font", smallFontProps);
        themesMap.put("Large-Font", largeFontProps);
        themesMap.put("Giant-Font", giantFontProps);

        themesMap.put("Green", greenProps);
        themesMap.put("Green-Small-Font", greenSmallFontProps);
        themesMap.put("Green-Large-Font", greenLargeFontProps);
        themesMap.put("Green-Giant-Font", greenGiantFontProps);

        themesMap.put("Lemmon", lemmonProps);
        themesMap.put("Lemmon-Small-Font", lemmonSmallFontProps);
        themesMap.put("Lemmon-Large-Font", lemmonLargeFontProps);
        themesMap.put("Lemmon-Giant-Font", lemmonGiantFontProps);

        themesMap.put("Red", redProps);
        themesMap.put("Red-Small-Font", redSmallFontProps);
        themesMap.put("Red-Large-Font", redLargeFontProps);
        themesMap.put("Red-Giant-Font", redGiantFontProps);
    }

    public static java.util.List getThemes() {
        return themesList;
    }

    public static Properties getThemeProperties(String name) {
        return ((Properties) themesMap.get(name));
    }

    public static void setTheme(String name) {
        if (myTheme != null) {
            myTheme.setInternalName(name);
        }
        setTheme((Properties) themesMap.get(name));
    }

    public static void setTheme(String name, String licenseKey, String logoString) {
        Properties props = (Properties) themesMap.get(name);
        props.put("licenseKey", licenseKey);
        props.put("logoString", logoString);
        if (myTheme != null) {
            myTheme.setInternalName(name);
        }
        setTheme(props);
    }

    public static void setTheme(Properties themesProps) {
        if (myTheme == null) {
            myTheme = new AcrylDefaultTheme();
        }
        if ((myTheme != null) && (themesProps != null)) {
            myTheme.setUp();
            myTheme.setProperties(themesProps);
            myTheme.setupColorArrs();
            AbstractLookAndFeel.setTheme(myTheme);
        }
    }

    public static void setCurrentTheme(Properties themesProps) {
        setTheme(themesProps);
    }

    public String getName() {
        return "Acryl";
    }

    public String getID() {
        return "Acryl";
    }

    public String getDescription() {
        return "The Acryl Look and Feel";
    }

    public boolean isNativeLookAndFeel() {
        return false;
    }

    public boolean isSupportedLookAndFeel() {
        return true;
    }

    public AbstractBorderFactory getBorderFactory() {
        return AcrylBorderFactory.getInstance();
    }

    public AbstractIconFactory getIconFactory() {
        return AcrylIconFactory.getInstance();
    }

    protected void createDefaultTheme() {
        if (myTheme == null) {
            myTheme = new AcrylDefaultTheme();
        }
        setTheme(myTheme);
    }

    protected void initClassDefaults(UIDefaults table) {
        super.initClassDefaults(table);
        Object[] uiDefaults = {
            // BaseLookAndFeel classes
            "ButtonUI", BaseButtonUI.class.getName(),
            "ToggleButtonUI", BaseToggleButtonUI.class.getName(),
            "LabelUI", BaseLabelUI.class.getName(),
            "SeparatorUI", BaseSeparatorUI.class.getName(),
            "TextFieldUI", BaseTextFieldUI.class.getName(),
            "TextAreaUI", BaseTextAreaUI.class.getName(),
            "EditorPaneUI", BaseEditorPaneUI.class.getName(),
            "CheckBoxUI", BaseCheckBoxUI.class.getName(),
            "RadioButtonUI", BaseRadioButtonUI.class.getName(),
            "SplitPaneUI", BaseSplitPaneUI.class.getName(),
            "ToolTipUI", BaseToolTipUI.class.getName(),
            "TreeUI", BaseTreeUI.class.getName(),
            "TableUI", BaseTableUI.class.getName(),
            "SliderUI", BaseSliderUI.class.getName(),
            "ProgressBarUI", BaseProgressBarUI.class.getName(),
            "ScrollPaneUI", BaseScrollPaneUI.class.getName(),
            "PanelUI", BasePanelUI.class.getName(),
            "TableHeaderUI", BaseTableHeaderUI.class.getName(),
            "FileChooserUI", BaseFileChooserUI.class.getName(),
            "MenuBarUI", BaseMenuBarUI.class.getName(),
            "MenuUI", BaseMenuUI.class.getName(),
            "PopupMenuUI", BasePopupMenuUI.class.getName(),
            "MenuItemUI", BaseMenuItemUI.class.getName(),
            "CheckBoxMenuItemUI", BaseCheckBoxMenuItemUI.class.getName(),
            "RadioButtonMenuItemUI", BaseRadioButtonMenuItemUI.class.getName(),
            "PopupMenuSeparatorUI", BaseSeparatorUI.class.getName(),
            // AcrylLookAndFeel classes
            "ComboBoxUI", AcrylComboBoxUI.class.getName(),
            "TabbedPaneUI", AcrylTabbedPaneUI.class.getName(),
            "ToolBarUI", AcrylToolBarUI.class.getName(),
            "InternalFrameUI", AcrylInternalFrameUI.class.getName(),
            "RootPaneUI", AcrylRootPaneUI.class.getName(),
            "ScrollBarUI", AcrylScrollBarUI.class.getName(),};
        table.putDefaults(uiDefaults);
        if (JTattooUtilities.getJavaVersion() >= 1.5) {
            table.put("SpinnerUI", AcrylSpinnerUI.class.getName());
        }
    }
}