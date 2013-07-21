package com.comphel.kumitesettings.business;

import android.os.Environment;

import com.comphel.common.definition.ShobuIpponFinalRules;
import com.comphel.common.definition.ShobuIpponNormalRules;

/**
 * Created by Admin on 20.07.13.
 */
public class KumiteSettingLoadingTool  {
    private static ShobuIpponFinalRules finalRules;
    private static ShobuIpponNormalRules normalRules;
    private static String configPath = Environment.getExternalStorageDirectory() + "/Comphel/ShobuIppon/";
    private static String config = "config.xml";

    public KumiteSettingLoadingTool(){
        finalRules = new ShobuIpponFinalRules();
        normalRules = new ShobuIpponNormalRules();
        load();
    }

    private static void load() {

    }

    public static ShobuIpponFinalRules getFinalRules() {
        load();
        return finalRules;
    }

    public static ShobuIpponNormalRules getNormalRules() {
        load();
        return normalRules;
    }
}
