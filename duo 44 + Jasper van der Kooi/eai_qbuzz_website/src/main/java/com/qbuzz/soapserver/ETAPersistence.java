package com.qbuzz.soapserver;

import java.util.ArrayList;

public class ETAPersistence {

	private static ArrayList<Bericht> berichten = new ArrayList<Bericht>();
	
	public static void addBericht(Bericht nieuwBericht) {
		berichten.add(nieuwBericht);
	}
	
	public static int getAantalBerichten() {
		return berichten.size();
	}
	
	public static Bericht getBericht(int index) {
		Bericht result = new Bericht();
		if (index < berichten.size()) {
				result=berichten.get(index);
		}
		return result;
	}
}
