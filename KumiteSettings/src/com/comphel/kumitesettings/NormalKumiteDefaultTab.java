package com.comphel.kumitesettings;

import android.os.Bundle;
import com.comphel.common.definition.ShobuIpponNormalRules;
import com.comphel.kumitesettings.business.KumiteSettingLoadingTool;

/**
 * Created by lede92 on 29.07.13.
 */
public class NormalKumiteDefaultTab extends KumiteDefaultTab {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initTools() {
        rules = KumiteSettingLoadingTool.getNormalRules();
    }

}
