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
        if(finalRules.isInConfiguration()){
        if(setupPage == 1){
            setupPage = 2;
            navButton.setImageResource(android.R.drawable.ic_media_rew);
            updateTabs(null, FinalKumiteSetupTab2.class);
        }
        else if ( setupPage == 2 ) {
            setupPage = 1;
            navButton.setImageResource(android.R.drawable.ic_media_ff);
            updateTabs(null, FinalKumiteSetupTab1.class);
        }
        }
        else if(normalRules.isInConfiguration()){
            if(setupPage == 1){
                setupPage = 2;
                navButton.setImageResource(android.R.drawable.ic_media_rew);
                updateTabs(NormalKumiteSetupTab2.class, null);
            }
            else if (setupPage == 2){
                setupPage = 1;
                navButton.setImageResource(android.R.drawable.ic_media_ff);
                updateTabs(NormalKumiteSetupTab1.class, null);
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
            updateTabs(null, FinalKumiteSetupTab1.class);
        }
        else{
            updateTabs(NormalKumiteSetupTab1.class, null);
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
        updateTabs(NormalKumiteDefaultTab.class, FinalKumiteDefaultTab.class);
    }

    private void initTools() {
        normalRules = KumiteSettingLoadingTool.getNormalRules();
        finalRules = KumiteSettingLoadingTool.getFinalRules();
    }

}