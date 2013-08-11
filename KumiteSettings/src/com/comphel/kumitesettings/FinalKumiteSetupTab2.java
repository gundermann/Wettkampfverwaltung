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
public class FinalKumiteSetupTab2 extends KumiteSetupTab2 {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onDestroy(){
        rules.setJogaiToLose(jogai.getValue());
        rules.setAtenaiToLose(atenai.getValue());
        rules.setMubobiToLose(mubobi.getValue());
        KumiteSettingSavingTool.saveFinal((ShobuIpponFinalRules) rules);
        super.onDestroy();
    }


    @Override
    public void initTools() {
        rules = KumiteSettingLoadingTool.getFinalRules();
    }
}
