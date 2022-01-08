package com.qbuzz.soapserver;

import java.util.ArrayList;

public class Bericht {
	public String lijnNaam;
	public String eindpunt;
	public String bedrijf;
	public String busID;
	public int tijd;
	public ArrayList<ETA> ETAs;
	
	Bericht(String lijnNaam, String eindpunt, String bedrijf, String busID, int tijd, ArrayList<ETA> ETAs){
		this.lijnNaam=lijnNaam;
		this.bedrijf=bedrijf;
		this.eindpunt=eindpunt;
		this.busID=busID;
		this.tijd=tijd;
		this.ETAs=ETAs;
	}

	Bericht() {
		this.lijnNaam="";
		this.bedrijf="";
		this.eindpunt="";
		this.busID="";
		this.tijd=0;
		this.ETAs=new ArrayList<ETA>();		
	}
}
