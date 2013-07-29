package com.comphel.kumitesettings;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.widget.TextView;

import com.comphel.common.definition.ShobuIpponFinalRules;
import com.comphel.common.definition.ShobuIpponNormalRules;
import com.comphel.common.definition.ShobuIpponRules;
import com.comphel.kumitesettings.business.KumiteSettingLoadingTool;

/**
 * Created by Admin on 20.07.13.
 */
abstract public class KumiteDefaultTab extends Activity {


    public ShobuIpponRules rules;
    private TextView tvTime;
    private TextView tvWazari;
    private TextView tvJogai;
    private TextView tvAtenai;
    private TextView tvMubobi;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.default_tab);

        initTools();

        tvTime = (TextView) findViewById(R.id.tvSettedTime);
        tvWazari = (TextView) findViewById(R.id.tvSettedWazari);
        tvJogai = (TextView) findViewById(R.id.tvSettedJogai);
        tvAtenai = (TextView) findViewById(R.id.tvSettedAtenai);
        tvMubobi = (TextView) findViewById(R.id.tvSettedMubobi);

        updateRules();
    }

    abstract public void initTools();

    public void updateRules() {
        getTvTime().setText(String.valueOf(rules.getTimeleft()/1000)+" sec");
        getTvWazari().setText(String.valueOf(rules.getWazariToWin()));
        getTvJogai().setText(String.valueOf(rules.getJogaiToLose()));
        getTvAtenai().setText(String.valueOf(rules.getAtenaiToLose()));
        getTvMubobi().setText(String.valueOf(rules.getMubobiToLose()));
    }

    public TextView getTvTime() {
        return tvTime;
    }

    public void setTvTime(TextView tvTime) {
        this.tvTime = tvTime;
    }

    public TextView getTvWazari() {
        return tvWazari;
    }

    public void setTvWazari(TextView tvWazari) {
        this.tvWazari = tvWazari;
    }

    public TextView getTvJogai() {
        return tvJogai;
    }

    public void setTvJogai(TextView tvJogai) {
        this.tvJogai = tvJogai;
    }

    public TextView getTvAtenai() {
        return tvAtenai;
    }

    public void setTvAtenai(TextView tvAtenai) {
        this.tvAtenai = tvAtenai;
    }

    public TextView getTvMubobi() {
        return tvMubobi;
    }

    public void setTvMubobi(TextView tvMubobi) {
        this.tvMubobi = tvMubobi;
    }

}
