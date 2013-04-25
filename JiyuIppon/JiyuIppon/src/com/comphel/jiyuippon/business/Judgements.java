package com.comphel.jiyuippon.business;

import com.comphel.jiyuippon.definition.Atenai;
import com.comphel.jiyuippon.definition.Jogai;
import com.comphel.jiyuippon.definition.Muobi;

public class Judgements {
	
	private Atenai atenai = null;
	
	private Jogai jogai = null;
	
	private Muobi muobi = null;
	
	private int score = 0;
	
	private boolean hansuko = false;
	

	public void addWazari(){
		score++;
	}
	
	public void addIppon(){
		score++;
		score++;
	}
	
	public int getScore() {
		return score;
	}

	public void addJogai() {
		switch (jogai) {
		case JOGAI:
			jogai = Jogai.JOGAICHUI;
			break;
		case JOGAICHUI:
			jogai = Jogai.JOGAIHANSUKO;
			setHansuko(true);
			break;
		default:
			jogai = Jogai.JOGAI;
		}
	}
	
	public void addMuobi() {
		switch (muobi) {
		case MUOBI:
			muobi = Muobi.MUOBICHUI;
			break;
		case MUOBICHUI:
			muobi = Muobi.MUOBIHANSUKO;
			setHansuko(true);
			break;
		default:
			muobi = Muobi.MUOBI;
		}
	}
	
	public void addAtenai() {
		switch (atenai) {
		case ATENAI:
			atenai = Atenai.ATENAICHUI;
			break;
		case ATENAICHUI:
			atenai = Atenai.ATENAIHANSUKO;
			setHansuko(true);
			break;
		default:
			atenai = Atenai.ATENAI;
		}
	}
	
	public boolean isHansuko() {
		return hansuko;
	}
	
	public void setHansuko(boolean hansuko) {
		this.hansuko = hansuko;
	}
}
