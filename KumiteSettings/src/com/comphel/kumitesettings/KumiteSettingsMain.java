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
import com.comphel.common.definition.ShobuIpponRules;
import com.comphel.kumitesettings.business.KumiteSettingLoadingTool;
import com.comphel.kumitesettings.business.KumiteSettingSavingTool;

/**
 * Created by lede92 on 25.06.13.
 */
public class KumiteSettingsMain extends TabActivity{

    static ShobuIpponNormalRules normalRules;
    static ShobuIpponFinalRules finalRules;
    int setupPage = 0;
    static int currentTab = 0;
    Button btSetupConfiguration;
    ImageButton navButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.shobuippon_setting);

        initTools();

        updateTabs(NormalKumiteDefaultTab.class, FinalKumiteDefaultTab.class);

        navButton = (ImageButton) findViewById(R.id.navButton);
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleNavButton();
            }
        });
        navButton.setEnabled(false);

        btSetupConfiguration = (Button) findViewById(R.id.mainButton);
        btSetupConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleMainbutton();
            }
        });
    }

    public void onChangeTab(){
        currentTab = getTabHost().getCurrentTab();
    }

    private void handleNavButton() {
        TabHost tabHost = getTabHost();
        saveContent();
        if(finalRules.isInConfiguration()){
        if(setupPage == 1){
            setupPage = 2;
            navButton.setImageResource(android.R.drawable.ic_media_rew);
            updateTabs(null, KumiteSetupTab2.class);
        }
        else if ( setupPage == 2 ) {
            setupPage = 1;
            navButton.setImageResource(android.R.drawable.ic_media_ff);
            updateTabs(null, KumiteSetupTab1.class);
        }
        }
        else if(normalRules.isInConfiguration()){
            if(setupPage == 1){
                setupPage = 2;
                navButton.setImageResource(android.R.drawable.ic_media_rew);
                updateTabs(KumiteSetupTab2.class, null);
            }
            else if (setupPage == 2){
                setupPage = 1;
                navButton.setImageResource(android.R.drawable.ic_media_ff);
                updateTabs(KumiteSetupTab1.class, null);
            }
        }
    }

    private void updateTabs(Class<? extends Activity> class1, Class<? extends Activity> class2) {
        TabHost tabHost = getTabHost();

        currentTab = tabHost.getCurrentTab();

        tabHost.clearAllTabs();
        if(class1 != null){
        Intent intentEvaluation = new Intent().setClass(this, class1 );
            intentEvaluation.addFlags(0);
        TabSpec tabSpecEvaluation = tabHost.newTabSpec("Evaluation").setIndicator("Evaluation").setContent(intentEvaluation);
        tabHost.addTab(tabSpecEvaluation);
        }

        if(class2 != null){
        Intent intentFinals = new Intent().setClass(this, class2);
            intentFinals.addFlags(1);
        TabSpec tabSpecFinals = tabHost.newTabSpec("Finals").setIndicator("Finals").setContent(intentFinals);
        tabHost.addTab(tabSpecFinals);
        }

        tabHost.setCurrentTab(currentTab);
    }


    private void handleMainbutton() {
        if(setupPage != 0){
            getTabHost().setEnabled(true);
            updateCurrentTabOutOfSetupPhase();
            setupPage = 0;
            btSetupConfiguration.setText(R.string.beginConfig);
        }
        else{
            getTabHost().setEnabled(false);
            updateCurrentTabIntoSetupPhase();
            setupPage = 1;
            btSetupConfiguration.setText(R.string.apply);
        }
    }

    private void saveContent() {
        if(setupPage != 0){
            View currentView = getTabHost().getCurrentTabView();
            if(getTabHost().getCurrentTab() == 0 && getTabHost().getCurrentTabView().equals(findViewById(R.layout.setup1_view)) ){
                normalRules.setTimeleft(Long.parseLong(((TextView) currentView.findViewById(R.id.tvSettedTime)).getText().toString()));
                normalRules.setWazariToWin(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedWazari)).getText().toString()));
            }
            else if(getTabHost().getCurrentTab() == 0 && getTabHost().getCurrentTabView().equals(findViewById(R.layout.setup2_view))){
                normalRules.setJogaiToLose(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedJogai)).getText().toString()));
                normalRules.setAtenaiToLose(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedAtenai)).getText().toString()));
                normalRules.setMubobiToLose(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedMubobi)).getText().toString()));

            }
            else if(getTabHost().getCurrentTab() == 1 && getTabHost().getCurrentTabView().equals(findViewById(R.layout.setup1_view))){
                finalRules.setTimeleft(Long.parseLong(((TextView) currentView.findViewById(R.id.tvSettedTime)).getText().toString()));
                finalRules.setWazariToWin(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedWazari)).getText().toString()));
            }
            else if(getTabHost().getCurrentTab() == 1 && getTabHost().getCurrentTabView().equals(findViewById(R.layout.setup2_view))){
                finalRules.setJogaiToLose(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedJogai)).getText().toString()));
                finalRules.setAtenaiToLose(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedAtenai)).getText().toString()));
                finalRules.setMubobiToLose(Integer.parseInt(((TextView) currentView.findViewById(R.id.tvSettedMubobi)).getText().toString()));

            }
        }
    }

    private void updateCurrentTabIntoSetupPhase() {
        getTabHost().setClickable(false);
        if(getTabHost().getCurrentTab() == 0){
            normalRules.setInConfiguration(true);
        }else{
            finalRules.setInConfiguration(true);
        }
        navButton.setEnabled(true);
        navButton.setClickable(true);
        if(getTabHost().getCurrentTab() == 1){
            updateTabs(null, KumiteSetupTab1.class);
        }
        else{
            updateTabs(KumiteSetupTab1.class, null);
        }
    }

    private void updateCurrentTabOutOfSetupPhase() {
        getTabHost().setClickable(true);
        if(getTabHost().getCurrentTab() == 0){
            normalRules.setInConfiguration(false);
        }else{
            finalRules.setInConfiguration(false);
        }
        navButton.setEnabled(false);
        navButton.setClickable(false);
        saveSetup();
        updateTabs(NormalKumiteDefaultTab.class, FinalKumiteDefaultTab.class);
    }

    private void initTools() {
        normalRules = KumiteSettingLoadingTool.getNormalRules();
        finalRules = KumiteSettingLoadingTool.getFinalRules();
    }

    private void saveSetup(){
        KumiteSettingSavingTool.save(normalRules, finalRules);
    }

}