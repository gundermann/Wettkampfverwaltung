package business;

import com.comphel.common.business.Stopwatch;
import com.comphel.common.definition.CompetitorNameInCompetition;
import definition.Config;

public class TimedMatch extends Thread{

	CompetitionListener compListener;
	
	boolean isFinale;
	
	private KumiteCompetitor winner;
	
	Stopwatch clock;
	
	KumiteCompetitor aka;
	
	KumiteCompetitor shiro;
	
	Config control;
	
	boolean isPaused = true;
	
	int round = 1;

	public TimedMatch(KumiteCompetitor aka, KumiteCompetitor shiro, Stopwatch clock, CompetitionListener compListener, boolean isFianle){
		this.aka = aka;
		this.shiro = shiro;
		this.clock = clock;
		this.compListener = compListener;
		this.clock.reset();
		this.isFinale=isFianle;
		
		control = new Config();
		control.update(isFinale);
	}
	
	public void run(){
//		clock.handleStart();
		while(!isFinished()){
			if(!isPaused){
				//time is over
			if(isOutOfTime()){
				pause();
				compListener.reactOnOutOfTime();
			}
			}
		}
	}

	public void pause(){
		clock.handleStopped();
		if(!isPaused)
			changeState();
	}
	
	public void changeState(){
		if(isPaused){
			isPaused=false;
		}
		else{
			isPaused = true;
		}
			
	}
	
	private boolean isOutOfTime() {
		return clock.getTime() > control.getTimeleft();
	}
	
	public void nextRound(){
		this.reset();
		this.round++;
	}
	
	public void wazari(CompetitorNameInCompetition comp){
//		pause();
		if(comp == CompetitorNameInCompetition.Aka){
			aka.getJudgement().addWazari();
		}
		else{
			shiro.getJudgement().addWazari();
		}
		
		evaluateWinner();
	}
	
	public void ippon(CompetitorNameInCompetition comp){
//		pause();
		if(comp == CompetitorNameInCompetition.Aka)		
			aka.getJudgement().addIppon();
		else
			shiro.getJudgement().addIppon();
		
		evaluateWinner();
	}	
	
	public void jogai(CompetitorNameInCompetition comp){
//		pause();
		if(comp == CompetitorNameInCompetition.Aka)
			aka.getJudgement().addJogai();
		else
			shiro.getJudgement().addJogai();
	
		evaluateWinner();
	}
	
	public void muobi(CompetitorNameInCompetition comp){
//		pause();
		if(comp == CompetitorNameInCompetition.Aka)
			aka.getJudgement().addMuobi();
		else
			shiro.getJudgement().addMuobi();
	
		evaluateWinner();
	}
	
	public void atenai(CompetitorNameInCompetition comp){
//		pause();
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
				setWinner(CompetitorNameInCompetition.Shiro);
			else if(shiro.getJudgement().isHansuko())
				setWinner(CompetitorNameInCompetition.Aka);
			else if(shiro.getJudgement().getScore() == control.getWazariToWin())
				setWinner(CompetitorNameInCompetition.Shiro);
			else
				setWinner(CompetitorNameInCompetition.Aka);
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
		clock.reset();
	}

	public KumiteCompetitor getAka() {
		return aka;
	}

	public KumiteCompetitor getShiro() {
		return shiro;
	}
	
	
}
