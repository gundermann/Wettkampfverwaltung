package com.comphel.jiyuippon;


import com.comphel.jiyuippon.business.Match;
import com.example.jiyuippon.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class JiyuIppon extends Activity {
	
	Match match;
	
	Dialog currentDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.setContentView(R.layout.activity_jiyu_ippon);
		
		updateLayout();
		
		if(match == null){
			openDialogToSetupCompetitors();
		}
		
		
		
		
	}
	
	

	private void updateLayout() {
		Display display = getWindowManager().getDefaultDisplay();
		
		LayoutParams linLayParams = new LayoutParams(display.getWidth()/2, display.getHeight()-heightOfStableElements());
		
		((LinearLayout) findViewById(R.id.RedLayout)).setLayoutParams(linLayParams);
		((LinearLayout) findViewById(R.id.WihteLayout)).setLayoutParams(linLayParams);
		
		
		// TODO Auto-generated method stub
		
	}

	private int heightOfStableElements() {
		return ((TextView) findViewById(R.id.headline)).getHeight() + getClock().getHeight() + getBtStart().getHeight();
		
	}



	private Button getBtStart() {
		return (Button) findViewById(R.id.startStopButton);
	}



	private Chronometer getClock() {
		return (Chronometer) findViewById(R.id.clock);
	}


	private void openDialogToSetupCompetitors() {
		
		// TODO Auto-generated method stub
		
	}

}
