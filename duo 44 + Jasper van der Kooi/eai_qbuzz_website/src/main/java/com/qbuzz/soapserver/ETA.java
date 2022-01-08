package com.qbuzz.soapserver;

public class ETA {

	public String halteNaam;
	public int richting;
	public int aankomsttijd;
	
	public ETA() {
		this.halteNaam = "";
		this.richting = 0;
		this.aankomsttijd = 0;
		
	}
	
	public ETA(String halteNaam, int richting, int aankomsttijd) {
		this.halteNaam = halteNaam;
		this.richting = richting;
		this.aankomsttijd = aankomsttijd;
	}
}
