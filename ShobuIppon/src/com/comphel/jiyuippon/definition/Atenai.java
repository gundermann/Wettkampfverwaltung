package com.comphel.jiyuippon.definition;

public enum Atenai {
	NONE, ATENAI, ATENAICHUI, ATENAIHANSUKO;
	
	public int toNumber(){
		switch (this) {
		case ATENAI:
			return 1;
		case ATENAICHUI:
			return 2;
		case ATENAIHANSUKO:
			return 3;
		default:
			return 0;
		}
	}
}
