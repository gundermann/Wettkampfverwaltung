package com.comphel.jiyuippon.business;

import android.os.SystemClock;
import android.widget.Chronometer;


public class Stopwatch {

	private Long lastTime;
	
	private Chronometer clock;
	
	public Stopwatch(Chronometer clock) {
		this.clock = clock;
		this.setLastTime(0L);
	}
	
	
	public void updateClock(Long time){
		this.lastTime = time;
	}
	
	/**
	 * Stops the clock
	 */
	public void handleStopped() {
		this.setLastTime(SystemClock.elapsedRealtime()-clock.getBase());
		clock.stop();
	}

	
	/**
	 * if clock was initialized the clock begin from 00:00
	 * if clock was stopped the clock begins from saved time
	 * @param state
	 * @throws NoClockStateException
	 */
	public void handleStart(){
		clock.setBase(SystemClock.elapsedRealtime()-this.lastTime);
		
		clock.start();
		
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
	
}
