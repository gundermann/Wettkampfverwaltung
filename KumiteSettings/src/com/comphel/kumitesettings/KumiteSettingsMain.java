package com.comphel.kumitesettings;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.comphel.common.definition.ShobuIpponFinalRules;
import com.comphel.common.definition.ShobuIpponNormalRules;
import com.comphel.kumitesettings.business.KumiteSettingLoadingTool;
import com.comphel.kumitesettings.business.KumiteSettingSavingTool;

/**
 * Created by lede92 on 25.06.13.
 */
public class KumiteSettingsMain extends TabActivity{

    ShobuIpponNormalRules normalRules;
    ShobuIpponFinalRules finalRules;
    boolean setupPhase = false;
    Button btSetupConfiguration;
    ImageButton navButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.shobuippon_setting);

        updateTabs(KumiteDefaultTab.class, KumiteDefaultTab.class);

        initTools();

        navButton = (ImageButton) findViewById(R.id.navButton);
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleNavButton();
            }
        });

        btSetupConfiguration = (Button) findViewById(R.id.mainButton);
        btSetupConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleMainbutton();
            }
        });
    }

    private void handleNavButton() {
        TabHost tabHost = getTabHost();

        if(tabHost.getCurrentTabView().equals(findViewById(R.layout.setup1_view))){
            navButton.setImageResource(android.R.drawable.ic_media_rew);
            updateTabs(KumiteSetupTab2.class, KumiteSetupTab2.class);
        }
        else{
            navButton.setImageResource(android.R.drawable.ic_media_ff);
            updateTabs(KumiteSetupTab1.class, KumiteSetupTab1.class);
        }
    }

    private void updateTabs(Class<? extends Activity> class1, Class<? extends Activity> class2) {
        TabHost tabHost = getTabHost();

        int currentTab = tabHost.getCurrentTab();

        if(setupPhase){
            View currentView = tabHost.getCurrentTabView();
            if(class1.equals(KumiteSetupTab1.class) ){
                normalRules.setTimeleft(Long.parseLong(((TextView) currentView.findViewById(R.id.tvSettedTime)).getText().toString()));
                normalRules.setWazariToWin(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedWazari)).getText().toString()));
            }
            else if(class1.equals(KumiteSetupTab2.class)){
                normalRules.setJogaiToLose(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedJogai)).getText().toString()));
                normalRules.setAtenaiToLose(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedAtenai)).getText().toString()));
                normalRules.setMubobiToLose(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedMubobi)).getText().toString()));

            }
            else if(class2.equals(KumiteSetupTab1.class) ){
                finalRules.setTimeleft(Long.parseLong(((TextView) currentView.findViewById(R.id.tvSettedTime)).getText().toString()));
                finalRules.setWazariToWin(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedWazari)).getText().toString()));
            }
            else if(class2.equals(KumiteSetupTab2.class)){
                finalRules.setJogaiToLose(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedJogai)).getText().toString()));
                finalRules.setAtenaiToLose(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedAtenai)).getText().toString()));
                finalRules.setMubobiToLose(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedMubobi)).getText().toString()));

            }
        }

        tabHost.removeAllViews();

        Intent intentEvaluation = new Intent().setClass(this, class1 );
        TabSpec tabSpecEvaluation = tabHost.newTabSpec("Evaluation").setIndicator("Evaluation").setContent(intentEvaluation);

        Intent intentFinals = new Intent().setClass(this, class2);
        TabSpec tabSpecFinals = tabHost.newTabSpec("Finals").setIndicator("Finals").setContent(intentFinals);

        tabHost.addTab(tabSpecEvaluation);
        tabHost.addTab(tabSpecFinals);

        tabHost.setCurrentTab(currentTab);
    }

    private void handleMainbutton() {
        if(setupPhase){
            getTabHost().setEnabled(true);
            updateCurrentTabOutOfSetupPhase();
            setupPhase = false;
            btSetupConfiguration.setText(R.string.apply);
        }
        else{
            getTabHost().setEnabled(false);
            updateCurrentTabIntoSetupPhase();
            setupPhase = true;
            btSetupConfiguration.setText(R.string.beginConfig);
        }
    }

    private void updateCurrentTabIntoSetupPhase() {
        if(getTabHost().getCurrentTab() == 1){
            updateTabs(KumiteDefaultTab.class, KumiteSetupTab1.class);
        }
        else{
            updateTabs(KumiteSetupTab1.class, KumiteDefaultTab.class);
        }
    }

    private void updateCurrentTabOutOfSetupPhase() {
        saveSetup();
        updateTabs(KumiteDefaultTab.class, KumiteDefaultTab.class);
    }

    private void initTools() {
        normalRules = KumiteSettingLoadingTool.getNormalRules();
        finalRules = KumiteSettingLoadingTool.getFinalRules();
    }

    private void saveSetup(){
        KumiteSettingSavingTool.save(normalRules, finalRules);
    }

}