package com.comphel.common.business;

import android.os.SystemClock;
import android.widget.Chronometer;


public class Stopwatch {

	private Long lastTime;
	
	private Chronometer clock;

	private boolean isStopped = true;
	
	public Stopwatch(Chronometer clock) {
		this.clock = clock;
		this.setLastTime(0L);
	}
	
	
	public void updateClockByTime(Long time){
		this.lastTime = time;
	}
	
	/**
	 * Stops the clock
	 */
	public void handleStopped() {
		this.setLastTime(SystemClock.elapsedRealtime()-clock.getBase());
		isStopped = true;
		clock.stop();
	}

	
	/**
	 * if clock was initialized the clock begin from 00:00
	 * if clock was stopped the clock begins from saved time
	 */
	public void handleStart(){
		clock.setBase(SystemClock.elapsedRealtime()-this.lastTime);
		isStopped = false;
		clock.start();
		
	}
	
	public void reset(){
		handleStopped();
		this.setLastTime(0L);
		handleStart();
		handleStopped();
		this.setLastTime(0L);
	}
	
	
	public Chronometer getClock() {
		return clock;
	}

	public Long getTime() {
		return SystemClock.elapsedRealtime()-clock.getBase();
	}
	
	
	public void setLastTime(Long time) {
		this.lastTime = time;
	}


	public void showTime() {
		clock.setBase(SystemClock.elapsedRealtime()-this.lastTime);
		clock.start();
		clock.stop();
	}


	public Long getLastTime() {
		return lastTime;
	}


	public boolean isStopped() {
		return isStopped;
	}
	
}
