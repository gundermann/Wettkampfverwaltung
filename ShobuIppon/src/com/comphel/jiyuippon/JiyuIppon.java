package com.comphel.jiyuippon;


import com.comphel.jiyuippon.business.CompetitionListener;
import com.comphel.jiyuippon.business.KumiteCompetitor;
import com.comphel.jiyuippon.business.Match;
import com.comphel.jiyuippon.business.Stopwatch;
import com.comphel.jiyuippon.definition.CompetitorNameInCompetition;
import com.example.jiyuippon.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class JiyuIppon extends Activity implements CompetitionListener{
	
	Match match;
	
	Dialog currentDialog;

	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.setContentView(R.layout.activity_jiyu_ippon);
		
		updateLayout();
		
		if(match == null){
			openDialogToSetupCompetitors();
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

		setDialog(alertDialogBuilder.create());
		
		dialog.show();		
		
		Button btAddCompetitior = (Button) dialog.findViewById(R.id.btAddCompetitors);
		btAddCompetitior.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				KumiteCompetitor aka = createNewKumiteCompetitor(((EditText) dialog.findViewById(R.id.akaFirstname)).getText().toString(), ((EditText) dialog.findViewById(R.id.akaLastname)).getText().toString());
				KumiteCompetitor shiro = createNewKumiteCompetitor(((EditText) dialog.findViewById(R.id.shiroFirstname)).getText().toString(), ((EditText) dialog.findViewById(R.id.shiroLastname)).getText().toString());
				
				createNewMatch(aka, shiro);
				dialog.cancel();
			}
			
			
		});
	}

	private void createNewMatch(KumiteCompetitor aka,
			KumiteCompetitor shiro) {
		match = new Match(aka, shiro, new Stopwatch(getClock()), this);
	}

	private KumiteCompetitor createNewKumiteCompetitor(String firstname, String lastname) {
		return new KumiteCompetitor(firstname, lastname, 10, null);
	}

	private void setDialog(AlertDialog createdDialog) {
		this.dialog = createdDialog;
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

		setDialog(alertDialogBuilder.create());
		
		Button btAkaWins = (Button) findViewById(R.id.btAkaWins);
		btAkaWins.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				match.setWinner(CompetitorNameInCompetition.Aka);
				dialog.cancel();
			}
		});
		
		Button btShiroWins = (Button) findViewById(R.id.btshiroWins);
		btShiroWins.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				match.setWinner(CompetitorNameInCompetition.Shiro);
				dialog.cancel();
			}
		});
		
		Button btHikewake = (Button) findViewById(R.id.btHikewake);
		if(match.getRound() != 3){
		btHikewake.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				match.reset();
				match.setRound(match.getRound()+1);
				dialog.cancel();
			}
		});
		}else{
			btHikewake.setEnabled(false);
		}
		
		dialog.show();
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

		setDialog(alertDialogBuilder.create());
		
		Button btNextMatch = (Button) findViewById(R.id.buttonNextMatch);
		btNextMatch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				match = null;
				openDialogToSetupCompetitors();
				dialog.cancel();
			}
		});
		
		dialog.show();
		// TODO Auto-generated method stub
		
	}

}
