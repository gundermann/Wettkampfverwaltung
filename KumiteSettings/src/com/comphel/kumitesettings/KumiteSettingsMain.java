package com.comphel.kumitesettings;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by lede92 on 25.06.13.
 */
public class KumiteSettingsMain extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.shobuippon_setting);
        android.widget.TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
      //  tabHost.newTabSpec("Evaluation").setContent(R.layout.tab_view);


    }
}