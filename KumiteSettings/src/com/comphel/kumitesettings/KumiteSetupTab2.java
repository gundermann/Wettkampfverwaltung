package com.comphel.kumitesettings;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by Admin on 19.07.13.
 */
abstract public class KumiteSetupTab2 extends KumiteTab {
    NumberPicker atenai;
    NumberPicker jogai;
    NumberPicker mubobi;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.setup2_view);

        jogai = (NumberPicker) findViewById(R.id.jogai);
        jogai.setMaxValue(100);
        jogai.setMinValue(1);
        jogai.setValue(rules.getJogaiToLose());

        atenai = (NumberPicker) findViewById(R.id.atenai);
        atenai.setMaxValue(100);
        atenai.setMinValue(1);
        atenai.setValue(rules.getAtenaiToLose());

        mubobi = (NumberPicker) findViewById(R.id.mubobi);
        mubobi.setMaxValue(100);
        mubobi.setMinValue(1);
        mubobi.setValue(rules.getMubobiToLose());
    }
}
