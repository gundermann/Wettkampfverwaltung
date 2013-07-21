package com.comphel.kumitesettings;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.widget.TextView;

import com.comphel.common.definition.ShobuIpponRules;

/**
 * Created by Admin on 20.07.13.
 */
public class KumiteDefaultTab extends Activity {

    private ShobuIpponRules rules;
    private TextView tvTime;
    private TextView tvWazari;
    private TextView tvJogai;
    private TextView tvAtenai;
    private TextView tvMubobi;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.default_tab);

        tvTime = (TextView) findViewById(R.id.tvSettedTime);
        tvWazari = (TextView) findViewById(R.id.tvSettedWazari);
        tvJogai = (TextView) findViewById(R.id.tvSettedJogai);
        tvAtenai = (TextView) findViewById(R.id.tvSettedAtenai);
        tvMubobi = (TextView) findViewById(R.id.tvSettedMubobi);
    }

    public void updateRules(ShobuIpponRules rules){
        this.rules = rules;

        tvTime.setText(String.valueOf(rules.getTimeleft()/1000)+" sec");
        tvWazari.setText(String.valueOf(rules.getWazariToWin()));
        tvJogai.setText(String.valueOf(rules.getJogaiToLose()));
        tvAtenai.setText(String.valueOf(rules.getAtenaiToLose()));
        tvMubobi.setText(String.valueOf(rules.getMubobiToLose()));
    }
}
