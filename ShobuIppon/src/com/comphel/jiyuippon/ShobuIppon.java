package com.comphel.jiyuippon;


import com.comphel.jiyuippon.business.CompetitionListener;
import com.comphel.jiyuippon.business.KumiteCompetitor;
import com.comphel.jiyuippon.business.Match;
import com.comphel.common.business.Stopwatch;
import com.comphel.common.definition.CompetitorNameInCompetition;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import static com.comphel.jiyuippon.R.id.RedLayout;

public class ShobuIppon extends Activity implements CompetitionListener{
	
	Match match;
	
	private Dialog currentDialog;

	private Builder dialogBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.setContentView(R.layout.activity_jiyu_ippon);

		updateLayout();
		
		if(match == null){
			initNewMatch();
		}
				
		getBtStart().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				match.changeState();
				if(!match.getClock().isStopped()){
					stopClock();
				}else{
					startClock();
				}
			}

			
		});
		
		//Initialization for Judging-Buttons
		//Aka
		getBtScoreAka().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				match.wazari(CompetitorNameInCompetition.Aka);
				getBtScoreAka().setText(String.valueOf(match.getAka().getJudgement().getScore()));
			}
		});
		
		getBtJogaiAka().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				match.jogai(CompetitorNameInCompetition.Aka);
				getBtJogaiAka().setText("J: " + String.valueOf(match.getAka().getJudgement().getJogai().toNumber()));
			}
		});
		
		getBtAtenaiAka().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				match.atenai(CompetitorNameInCompetition.Aka);
				getBtAtenaiAka().setText("A: " + String.valueOf(match.getAka().getJudgement().getAtenai().toNumber()));
			}
		});
		
		getBtMubobiAka().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				match.muobi(CompetitorNameInCompetition.Aka);
				getBtMubobiAka().setText("M: " + String.valueOf(match.getAka().getJudgement().getMuobi().toNumber()));
			}
		});
		
		//Shiro
				getBtScoreShiro().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						match.wazari(CompetitorNameInCompetition.Shiro);
						getBtScoreShiro().setText(String.valueOf(match.getShiro().getJudgement().getScore()));
					}
				});
				
				getBtJogaiShiro().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						match.jogai(CompetitorNameInCompetition.Shiro);
						getBtJogaiShiro().setText("J: " + String.valueOf(match.getShiro().getJudgement().getJogai().toNumber()));
					}
				});
				
				getBtAtenaiShiro().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						match.atenai(CompetitorNameInCompetition.Shiro);
						getBtAtenaiShiro().setText("A: " + String.valueOf(match.getShiro().getJudgement().getAtenai().toNumber()));
					}
				});
				
				getBtMubobiShiro().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						match.muobi(CompetitorNameInCompetition.Shiro);
						getBtMubobiShiro().setText("M: " + String.valueOf(match.getShiro().getJudgement().getMuobi().toNumber()));
					}
				});
	}
	
	protected void updateStrings() {
		String akaName =  this.match.getAka().toString();
		if( akaName.equals(" "))
			akaName = CompetitorNameInCompetition.Aka.toString();

		String shiroName = this.match.getShiro().toString();
		if(shiroName.equals(" "))
			shiroName = CompetitorNameInCompetition.Shiro.toString();
		
		((TextView) findViewById(R.id.tvAka)).setText( akaName );
		((TextView) findViewById(R.id.tvshiro)).setText( shiroName );
	}

	private void startClock() {
		match.getClock().handleStart();
		getBtStart().setText(R.string.stop);
	}
	
	private void stopClock() {
		match.getClock().handleStopped();
		getBtStart().setText(R.string.start);
	}
	
	@SuppressWarnings("deprecation")
	private void updateLayout() {
		Display display = getWindowManager().getDefaultDisplay();
		
		int margin = 2*2;
		int height  = display.getHeight();
		
		LayoutParams params = new LayoutParams(display.getWidth(), height/7);
		
		getClock().setLayoutParams(params);
		getBtStart().setLayoutParams(params);
		
		params = new LayoutParams(display.getWidth()/2, height);
		
		((LinearLayout) findViewById(RedLayout)).setLayoutParams(params);
		((LinearLayout) findViewById(R.id.WihteLayout)).setLayoutParams(params);
		
		params = new LayoutParams(display.getWidth()/4, 3*(height/7));
		
		LinearLayout layoutAkaJudging = ((LinearLayout) findViewById(R.id.layoutAkaJudging));
		layoutAkaJudging.setLayoutParams(params);
		((LinearLayout) findViewById(R.id.layoutShiroJudging)).setLayoutParams(params);
		
		((LinearLayout) findViewById(R.id.layoutAkaScore)).setLayoutParams(params);
		((LinearLayout) findViewById(R.id.layoutShiroScore)).setLayoutParams(params);
		
		params = new LayoutParams((display.getWidth()/4)-margin, (3*(height/7))-margin);
		params.setMargins(2, 2, 2, 2);
		
		getBtScoreAka().setLayoutParams(params);
		getBtScoreShiro().setLayoutParams(params);
		
		params = new LayoutParams((display.getWidth()/4)-margin, (height/7)-margin);
		params.setMargins(2, 2, 2, 2);
		
		getBtAtenaiAka().setLayoutParams(params);
		getBtAtenaiShiro().setLayoutParams(params);
		getBtMubobiAka().setLayoutParams(params);
		getBtMubobiShiro().setLayoutParams(params);
		getBtJogaiAka().setLayoutParams(params);
		getBtJogaiShiro().setLayoutParams(params);
		
	}


	
	private Button getBtScoreAka() {
		return (Button) findViewById(R.id.ButtonScoreAka);
	}
	
	private Button getBtMubobiAka() {
		return ((Button) findViewById(R.id.ButtonMuobiAka));
	}

	private Button getBtJogaiAka() {
		return ((Button) findViewById(R.id.ButtonJogaiAka));
	}

	private Button getBtAtenaiAka() {
		return ((Button) findViewById(R.id.ButtonAtenaiAka));
	}

	private Button getBtScoreShiro() {
		return (Button) findViewById(R.id.ButtonScoreshiro);
	}
	
	private Button getBtMubobiShiro() {
		return ((Button) findViewById(R.id.ButtonMuobiShiro));
	}

	private Button getBtJogaiShiro() {
		return ((Button) findViewById(R.id.ButtonJogaiShiro));
	}

	private Button getBtAtenaiShiro() {
		return ((Button) findViewById(R.id.ButtonAtenaiAshiro));
	}
	
//	private int heightOfStableElements() {
//		return 2*((TextView) findViewById(R.id.headline)).getHeight() ;
////		+ getClock().getHeight() ;
//		//+ getBtStart().getHeight();
//		
//	}

	private Button getBtStart() {
		return (Button) findViewById(R.id.buttonStartStop);
	}

	private Chronometer getClock() {
		return (Chronometer) findViewById(R.id.clock);
	}

	private void openDialogToSetupCompetitors() {
		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.dialog_enter_competitiors, null);

		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);

		alertDialogBuilder.setView(promptsView);
		alertDialogBuilder.setCancelable(false);

		setDialog(alertDialogBuilder);
		
		new DialogForSetupCompetitors(this);
	}
	
	private void setDialog(Builder alertDialogBuilder) {
		this.dialogBuilder = alertDialogBuilder;
	}

	@Override
	public void reactOnOutOfTime() {
		openDialogForRefereeDecision();
	}
	
	private void openDialogForRefereeDecision() {
		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.dialog_jurie_judegment_by_flag, null);

		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);

		alertDialogBuilder.setView(promptsView);

		setDialog(alertDialogBuilder);
		
		new DialogForRefereeDecision(this);
	}

	@Override
	public void reactOnEvaluatedWinner() {
		match.pause();
		stopClock();
		openDialogForFinishing();
	}

	private void openDialogForFinishing() {
		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.dialog_finished, null);

		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);

		alertDialogBuilder.setView(promptsView);

		setDialog(alertDialogBuilder);

		new DialogForFinishing(this);
	}
	
	public Builder getDialogBuilder() {
		return this.dialogBuilder;
	}

	public void createNewMatch(KumiteCompetitor aka, KumiteCompetitor shiro, boolean isFianle) {
		this.match = new Match(aka, shiro, new Stopwatch(getClock()), this, isFianle );
	}

	public void initNewMatch() {
		match = null;
		openDialogToSetupCompetitors();		
	}

	public void reset() {
		getBtStart().setText("Start");
		
		getBtAtenaiAka().setText(R.string.atenaiAka);
		getBtAtenaiShiro().setText(R.string.atenaiShiro);
		
		getBtMubobiAka().setText(R.string.muobiAka);
		getBtMubobiShiro().setText(R.string.muobiShiro);
		
		getBtJogaiAka().setText(R.string.jogaiAka);
		getBtJogaiShiro().setText(R.string.jogaiShiro);
		
		getBtScoreAka().setText(R.string.scoreAka);
		getBtScoreShiro().setText(R.string.scoreShiro);
	}
	
	@Override
	public void onBackPressed(){
		this.finish();
	}

}
