package com.comphel.jiyuippon.business;

import com.comphel.jiyuippon.definition.Config;

public class Match extends Thread{

	boolean isFinal;
	
	Stopwatch clock;
	
	KumiteCompetitor aka;
	
	KumiteCompetitor shiro;
	
	Config control;
	
	int round = 1;

	private boolean isPaused;
	
	public Match(KumiteCompetitor aka, KumiteCompetitor shiro){
		this.aka = aka;
		this.shiro = shiro;
		
		control = new Config();
		control.update(isFinal);
	}
	
	public void run(){
		clock.handleStart();
		isPaused = false;
		while(!isOutOfTime() || !isPaused()){
			
		}
	}

	public void pause(){
		isPaused = true;
		clock.handleStopped();
	}
	
	private boolean isOutOfTime() {
		return clock.getTime() > control.getTimeleft();
	}
	
	private boolean isPaused() {
		return isPaused ;
	}

	public void nextRound(){
		this.round++;
	}
	
	public void wazari(String comp){
		pause();
		if(comp.equals("aka")){
			aka.getJudgement().addWazari();
		}
		shiro.getJudgement().addWazari();
	}
	
	public void ippon(String comp){
		pause();
		if(comp.equals("aka"))		
			aka.getJudgement().addIppon();
		else
			shiro.getJudgement().addIppon();
	}	
	
	public void jogai(String comp){
		pause();
		if(comp.equals("aka"))
			aka.getJudgement().addJogai();
		else
			shiro.getJudgement().addJogai();
	}
	
	public void muobi(String comp){
		pause();
		if(comp.equals("aka"))
			aka.getJudgement().addMuobi();
		else
			shiro.getJudgement().addMuobi();
	}
	
	public void atenai(String comp){
		pause();
		if(comp.equals("aka"))
			aka.getJudgement().addAtenai();
		else
			shiro.getJudgement().addAtenai();
	}
	
	
	public boolean isFinished(){
		return aka.getJudgement().isHansuko() || shiro.getJudgement().isHansuko() || aka.getJudgement().getScore() == control.getWazariToWin() || shiro.getJudgement().getScore() == control.getWazariToWin(); 
	}
	
	public KumiteCompetitor getWinner(){
		if(isFinished()){
			if(aka.getJudgement().isHansuko())
				return shiro;
			else if(shiro.getJudgement().getScore() == control.getWazariToWin())
				return shiro;
			return aka;
		}
		else{
			return null;
		}
	}
	
}
