package com.comphel.kumitesettings;

import android.os.Bundle;
import android.widget.TextView;
import com.comphel.common.definition.ShobuIpponFinalRules;
import com.comphel.common.definition.ShobuIpponNormalRules;
import com.comphel.kumitesettings.business.KumiteSettingLoadingTool;
import com.comphel.kumitesettings.business.KumiteSettingSavingTool;

/**
 * Created by lede92 on 31.07.13.
 */
public class FinalKumiteSetupTab1 extends KumiteSetupTab1 {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onDestroy(){
        rules.setTimeleft((time1.getValue()*60+time2.getValue())*1000);
        rules.setWazariToWin(wazari.getValue());
        KumiteSettingSavingTool.saveFinal((ShobuIpponFinalRules) rules);
        super.onDestroy();
    }

    @Override
    public void initTools() {
        rules = KumiteSettingLoadingTool.getFinalRules();
    }
}
