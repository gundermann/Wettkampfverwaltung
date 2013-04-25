package com.comphel.jiyuippon.business;

import com.comphel.jiyuippon.definition.Graduierung;

public class KumiteCompetitor extends Competitor{

	private Judgements judgement = new Judgements();
	
	public KumiteCompetitor(String vorname, String nachname, int alter,
			Graduierung grad) {
		super(vorname, nachname, alter, grad);
	}

	public Judgements getJudgement() {
		return judgement;
	}

	
}
