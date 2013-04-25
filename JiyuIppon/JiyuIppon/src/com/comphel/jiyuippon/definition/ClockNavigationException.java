package com.comphel.jiyuippon.definition;

public class ClockNavigationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3784078986175651617L;

	public ClockNavigationException(String action){
		super("Can't execute "+action+" in this context.");
	}
}
