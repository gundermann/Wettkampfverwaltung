package com.comphel.jiyuippon.definition;

public class ConfigNotCompleteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7533540643688127625L;

	public ConfigNotCompleteException() {
		super("Config is not completely defined");
	}
}
