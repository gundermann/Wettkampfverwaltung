package business;


import definition.Atenai;
import definition.Jogai;
import definition.Mubobi;

public class Judgements {
	
	private Atenai atenai = Atenai.NONE;
	
	private Jogai jogai = Jogai.NONE;
	
	private Mubobi muobi = Mubobi.NONE;
	
	private int score = 0;
	
	private boolean hansuko = false;
	

	
	
	public Atenai getAtenai() {
		return atenai;
	}

	public Jogai getJogai() {
		return jogai;
	}

	public Mubobi getMuobi() {
		return muobi;
	}

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
			muobi = Mubobi.MUOBICHUI;
			break;
		case MUOBICHUI:
			muobi = Mubobi.MUOBIHANSUKO;
			setHansuko(true);
			break;
		default:
			muobi = Mubobi.MUOBI;
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

	public void clear() {
		atenai = Atenai.NONE;
		jogai = Jogai.NONE;
		score = 0;
		muobi = Mubobi.NONE;
	}
}
