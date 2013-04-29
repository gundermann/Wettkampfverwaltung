package com.comphel.jiyuippon.business;

import com.comphel.jiyuippon.definition.CompetitorNameInCompetition;
import com.comphel.jiyuippon.definition.Config;

public class Match extends Thread{

	CompetitionListener compListener;
	
	boolean isFinal;
	
	private KumiteCompetitor winner;
	
	Stopwatch clock;
	
	KumiteCompetitor aka;
	
	KumiteCompetitor shiro;
	
	Config control;
	
	int round = 1;

	public Match(KumiteCompetitor aka, KumiteCompetitor shiro, Stopwatch clock, CompetitionListener compListener){
		this.aka = aka;
		this.shiro = shiro;
		this.clock = clock;
		this.compListener = compListener;
		
		control = new Config();
		control.update(isFinal);
	}
	
	public void run(){
//		clock.handleStart();
		while(!isOutOfTime()){
			
		}
		//time is over
		compListener.reactOnOutOfTime();
	}

	public void pause(){
		clock.handleStopped();
	}
	
	private boolean isOutOfTime() {
		return clock.getTime() > control.getTimeleft();
	}
	
	public void nextRound(){
		this.round++;
	}
	
	public void wazari(CompetitorNameInCompetition comp){
		pause();
		if(comp == CompetitorNameInCompetition.Aka){
			aka.getJudgement().addWazari();
		}
		shiro.getJudgement().addWazari();
		
		evaluateWinner();
	}
	
	public void ippon(CompetitorNameInCompetition comp){
		pause();
		if(comp == CompetitorNameInCompetition.Aka)		
			aka.getJudgement().addIppon();
		else
			shiro.getJudgement().addIppon();
		
		evaluateWinner();
	}	
	
	public void jogai(CompetitorNameInCompetition comp){
		pause();
		if(comp == CompetitorNameInCompetition.Aka)
			aka.getJudgement().addJogai();
		else
			shiro.getJudgement().addJogai();
	
		evaluateWinner();
	}
	
	public void muobi(CompetitorNameInCompetition comp){
		pause();
		if(comp == CompetitorNameInCompetition.Aka)
			aka.getJudgement().addMuobi();
		else
			shiro.getJudgement().addMuobi();
	
		evaluateWinner();
	}
	
	public void atenai(CompetitorNameInCompetition comp){
		pause();
		if(comp == CompetitorNameInCompetition.Aka)
			aka.getJudgement().addAtenai();
		else
			shiro.getJudgement().addAtenai();
		
		evaluateWinner();
	}
	
	
	public boolean isFinished(){
		return winner != null || aka.getJudgement().isHansuko() || shiro.getJudgement().isHansuko() || aka.getJudgement().getScore() == control.getWazariToWin() || shiro.getJudgement().getScore() == control.getWazariToWin(); 
	}
	
	public KumiteCompetitor getWinner(){
		return this.winner;
	}
	
	private void evaluateWinner(){
		if(isFinished()){
			if(aka.getJudgement().isHansuko())
				winner = shiro;
			else if(shiro.getJudgement().getScore() == control.getWazariToWin())
				winner = shiro;
			winner = aka;
		}
	}

	public void setWinner(CompetitorNameInCompetition comp) {
		if(comp == CompetitorNameInCompetition.Aka)
			this.winner = this.aka;
		else
			this.winner = this.shiro;
		
		compListener.reactOnEvaluatedWinner();
	}
	
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	
	
	public Stopwatch getClock() {
		return clock;
	}

	public void reset() {
		aka.clearAllJudgements();
		shiro.clearAllJudgements();
	}
	
}
