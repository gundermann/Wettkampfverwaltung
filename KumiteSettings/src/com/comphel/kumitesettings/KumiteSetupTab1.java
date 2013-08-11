package com.comphel.kumitesettings;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by Admin on 16.07.13.
 */
abstract public class KumiteSetupTab1 extends KumiteTab {

    public NumberPicker time1;
    public NumberPicker time2;
    public NumberPicker wazari;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.setup1_view);

        time1 = (NumberPicker) findViewById(R.id.time1);
        time1.setMinValue(0);
        time1.setMaxValue(20);
        time1.setValue((int) ((rules.getTimeleft()/1000)/60));

        time2 = (NumberPicker) findViewById(R.id.time2);
        time2.setMinValue(0);
        time2.setMaxValue(59);
        time2.setValue((int) ((rules.getTimeleft()/1000)%60));


        wazari= (NumberPicker) findViewById(R.id.wazari);
        wazari.setMinValue(1);
        wazari.setMaxValue(20);
        wazari.setValue(rules.getWazariToWin());
    }


}
