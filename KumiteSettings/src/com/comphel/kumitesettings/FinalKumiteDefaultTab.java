package com.comphel.kumitesettings;

import com.comphel.kumitesettings.business.KumiteSettingLoadingTool;

/**
 * Created by lede92 on 29.07.13.
 */
public class FinalKumiteDefaultTab extends KumiteDefaultTab {
    @Override
    public void initTools() {
        rules = KumiteSettingLoadingTool.getFinalRules();
    }

}
