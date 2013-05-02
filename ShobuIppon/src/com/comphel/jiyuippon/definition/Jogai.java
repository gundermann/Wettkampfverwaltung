package com.comphel.jiyuippon.definition;

public enum Jogai {
	NONE, JOGAI, JOGAICHUI, JOGAIHANSUKO;
	
	public int toNumber(){
		switch (this) {
		case JOGAI:
			return 1;
		case JOGAICHUI:
			return 2;
		case JOGAIHANSUKO:
			return 3;
		default:
			return 0;
		}
	}
}
