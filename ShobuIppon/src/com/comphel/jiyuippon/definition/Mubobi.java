package com.comphel.jiyuippon.definition;

public enum Mubobi {
	NONE, MUOBI, MUOBICHUI, MUOBIHANSUKO;
	
	public int toNumber(){
		switch (this) {
		case MUOBI:
			return 1;
		case MUOBICHUI:
			return 2;
		case MUOBIHANSUKO:
			return 3;
		default:
			return 0;
		}
	}
}
