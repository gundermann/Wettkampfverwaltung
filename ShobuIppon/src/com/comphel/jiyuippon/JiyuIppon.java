package com.comphel.jiyuippon;


import com.comphel.jiyuippon.business.CompetitionListener;
import com.comphel.jiyuippon.business.KumiteCompetitor;
import com.comphel.jiyuippon.business.Match;
import com.comphel.jiyuippon.business.Stopwatch;
import com.comphel.jiyuippon.definition.CompetitorNameInCompetition;
import com.example.jiyuippon.R;

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

public class JiyuIppon extends Activity implements CompetitionListener{
	
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
				if(match.isAlive() && !match.isInterrupted()){
					match.getClock().handleStopped();
					match.interrupt();
				}else{
					match.getClock().handleStart();
					match.start();
				}
			}
		});
		
		//Initialization for Judging-Buttons
		//Aka
		getBtScoreAka().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				match.interrupt();
				match.wazari(CompetitorNameInCompetition.Aka);
			}
		});
		
		getBtJogaiAka().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				match.interrupt();
				match.jogai(CompetitorNameInCompetition.Aka);
			}
		});
		
		getBtAtenaiAka().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				match.interrupt();
				match.atenai(CompetitorNameInCompetition.Aka);
			}
		});
		
		getBtMuobiAka().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				match.interrupt();
				match.muobi(CompetitorNameInCompetition.Aka);
			}
		});
		
		//Shiro
				getBtScoreShiro().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						match.interrupt();
						match.wazari(CompetitorNameInCompetition.Shiro);
					}
				});
				
				getBtJogaiShiro().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						match.interrupt();
						match.jogai(CompetitorNameInCompetition.Shiro);
					}
				});
				
				getBtAtenaiShiro().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						match.interrupt();
						match.atenai(CompetitorNameInCompetition.Shiro);
					}
				});
				
				getBtMuobiShiro().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						match.interrupt();
						match.muobi(CompetitorNameInCompetition.Shiro);
					}
				});
	}
	
	protected void updateStrings() {
		((TextView) findViewById(R.id.tvAka)).setText( this.match.getAka().toString());
		((TextView) findViewById(R.id.tvshiro)).setText( this.match.getShiro().toString());
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


	private View getBtScoreAka() {
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

	private View getBtScoreShiro() {
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
		match.getClock().handleStopped();
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
		openDialogForFinishing();
	}

	private void openDialogForFinishing() {
		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.dialog_jurie_judegment_by_flag, null);

		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);

		alertDialogBuilder.setView(promptsView);

		setDialog(alertDialogBuilder);

		new DialogForFinishing(this);
	}
	
	public Builder getDialogBuilder() {
		return this.dialogBuilder;
	}

	public void createNewMatch(KumiteCompetitor aka, KumiteCompetitor shiro) {
		this.match = new Match(aka, shiro, new Stopwatch(getClock()), this);
	}

	public void initNewMatch() {
		match = null;
		openDialogToSetupCompetitors();		
	}

}
