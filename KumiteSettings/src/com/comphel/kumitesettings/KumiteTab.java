package com.comphel.kumitesettings;

import android.app.Activity;
import android.os.Bundle;
import com.comphel.common.definition.ShobuIpponRules;
import com.comphel.kumitesettings.business.KumiteSettingSavingTool;

/**
 * Created by lede92 on 31.07.13.
 */
abstract public class KumiteTab extends Activity {

    public ShobuIpponRules rules;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTools();
    }


    abstract public void initTools();
}
