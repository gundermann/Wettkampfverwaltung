package com.comphel.jiyuippon.definition;

public enum Graduierung {

	KYU9, KYU8, KYU7, KYU6, KYU5, KYU4, KYU3, KYU2, KYU1, DAN1, DAN2, DAN3, DAN4, DAN5, DAN6, DAN7, DAN8, DAN9;

	public static Graduierung getGraduierung(String grad){
		if(grad.equals("9.Kyu")){
		return Graduierung.KYU9;
	}
		if(grad.equals("8.Kyu")){
			return Graduierung.KYU9;
		}
		if(grad.equals("7.Kyu")){
			return Graduierung.KYU9;
		}
		if(grad.equals("6.Kyu")){
			return Graduierung.KYU9;
		}
		if(grad.equals("5.Kyu")){
			return Graduierung.KYU9;
		}
		if(grad.equals("4.Kyu")){
			return Graduierung.KYU9;
		}
		if(grad.equals("3.Kyu")){
			return Graduierung.KYU9;
		}
		if(grad.equals("2.Kyu")){
			return Graduierung.KYU9;
		}
		if(grad.equals("1.Kyu")){
			return Graduierung.KYU9;
		}

		return Graduierung.DAN9;
	}
}
