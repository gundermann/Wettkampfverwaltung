package com.comphel.jiyuippon.definition;

public enum CompetitorNameInCompetition {
	Aka, Shiro;

	public String toString(){
		if(this == Aka)
			return "Aka";
		else
			return "Shiro";
	}
}
