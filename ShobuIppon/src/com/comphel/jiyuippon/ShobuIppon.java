package com.comphel.jiyuippon;


import com.comphel.jiyuippon.business.CompetitionListener;
import com.comphel.jiyuippon.business.KumiteCompetitor;
import com.comphel.jiyuippon.business.Match;
import com.comphel.jiyuippon.business.Stopwatch;
import com.comphel.jiyuippon.definition.CompetitorNameInCompetition;
import com.comphel.jiyuippon.R;

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

public class ShobuIppon extends Activity implements CompetitionListener{
	
	Match match;
	
	Dialog currentDialog;

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
//					match.interrupt();
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
//				stopClock();
				match.wazari(CompetitorNameInCompetition.Aka);
				getBtScoreAka().setText(String.valueOf(match.getAka().getJudgement().getScore()));
			}
		});
		
		getBtJogaiAka().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				stopClock();
				match.jogai(CompetitorNameInCompetition.Aka);
				getBtJogaiAka().setText("J: " + String.valueOf(match.getAka().getJudgement().getJogai().toNumber()));
			}
		});
		
		getBtAtenaiAka().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				stopClock();
				match.atenai(CompetitorNameInCompetition.Aka);
				getBtAtenaiAka().setText("A: " + String.valueOf(match.getAka().getJudgement().getAtenai().toNumber()));
			}
		});
		
		getBtMuobiAka().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				stopClock();
				match.muobi(CompetitorNameInCompetition.Aka);
				getBtMuobiAka().setText("M: " + String.valueOf(match.getAka().getJudgement().getMuobi().toNumber()));
			}
		});
		
		//Shiro
				getBtScoreShiro().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
//						stopClock();
						match.wazari(CompetitorNameInCompetition.Shiro);
						getBtScoreShiro().setText(String.valueOf(match.getShiro().getJudgement().getScore()));
					}
				});
				
				getBtJogaiShiro().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
//						stopClock();
						match.jogai(CompetitorNameInCompetition.Shiro);
						getBtJogaiShiro().setText("J: " + String.valueOf(match.getShiro().getJudgement().getJogai().toNumber()));
					}
				});
				
				getBtAtenaiShiro().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
//						stopClock();
						match.atenai(CompetitorNameInCompetition.Shiro);
						getBtAtenaiShiro().setText("A: " + String.valueOf(match.getShiro().getJudgement().getAtenai().toNumber()));
					}
				});
				
				getBtMuobiShiro().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
//						stopClock();
						match.muobi(CompetitorNameInCompetition.Shiro);
						getBtMuobiShiro().setText("M: " + String.valueOf(match.getShiro().getJudgement().getMuobi().toNumber()));
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
		
		LayoutParams linLayParams = new LayoutParams(display.getWidth()/2, display.getHeight()-heightOfStableElements());
		
		((LinearLayout) findViewById(R.id.RedLayout)).setLayoutParams(linLayParams);
		((LinearLayout) findViewById(R.id.WihteLayout)).setLayoutParams(linLayParams);
		
		linLayParams = new LayoutParams(display.getWidth()/4, LayoutParams.WRAP_CONTENT);
		
		LinearLayout layoutAkaJudging = ((LinearLayout) findViewById(R.id.layoutAkaJudging));
		layoutAkaJudging.setLayoutParams(linLayParams);
		((LinearLayout) findViewById(R.id.layoutShiroJudging)).setLayoutParams(linLayParams);
		
		linLayParams = new LayoutParams(display.getWidth()/4, LayoutParams.WRAP_CONTENT);
		
		((LinearLayout) findViewById(R.id.layoutAkaScore)).setLayoutParams(linLayParams);
		((LinearLayout) findViewById(R.id.layoutShiroScore)).setLayoutParams(linLayParams);
	}


	
	private Button getBtScoreAka() {
		return (Button) findViewById(R.id.ButtonScoreAka);
	}
	
	private Button getBtMuobiAka() {
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
	
	private Button getBtMuobiShiro() {
		return ((Button) findViewById(R.id.ButtonMuobiShiro));
	}

	private Button getBtJogaiShiro() {
		return ((Button) findViewById(R.id.ButtonJogaiShiro));
	}

	private Button getBtAtenaiShiro() {
		return ((Button) findViewById(R.id.ButtonAtenaiAshiro));
	}
	
	private int heightOfStableElements() {
		return ((TextView) findViewById(R.id.headline)).getHeight() + getClock().getHeight() + getBtStart().getHeight();
		
	}

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
		
		getBtMuobiAka().setText(R.string.muobiAka);
		getBtMuobiShiro().setText(R.string.muobiShiro);
		
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
