package com.comphel.jiyuippon.business;

import com.comphel.jiyuippon.definition.Graduierung;

public class Competitor {

	private int alter;

	private String vorname;

	private String nachname;

	private Graduierung grad;
	
	public Competitor(String vorname, String nachname, int alter, Graduierung grad){
		this.vorname = vorname;
		this.nachname = nachname;
		this.alter = alter;
		this.grad = grad;
	}

	public int getAlter() {
		return alter;
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public Graduierung getGrad() {
		return grad;
	}

	public String toString(){
		return vorname + " " + nachname;
	}

}