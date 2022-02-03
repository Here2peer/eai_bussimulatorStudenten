package bussimulator;

import bussimulator.Halte.Positie;

public class Bus{

	private Bedrijven bedrijf;
	private Lijnen lijn;
	private int halteNummer;
	private int totVolgendeHalte;
	private int richting;
	private boolean bijHalte;
	private String busID;
	
	Bus(Lijnen lijn, Bedrijven bedrijf, int richting){
		this.lijn=lijn;
		this.bedrijf=bedrijf;
		this.richting=richting;
		this.halteNummer = -1;
		this.totVolgendeHalte = 0;
		this.bijHalte = false;
		this.busID = "Niet gestart";
	}

	public Lijnen getLijn(){
		return this.lijn;
	}

	public Bedrijven getBedrijf(){
		return this.bedrijf;
	}

	public int getHalteNummer(){
		return this.halteNummer;
	}

	public int getRichting(){
		return this.richting;
	}

	public boolean getBijHalte(){
		return this.bijHalte;
	}

	public int getTotVolgendeHalte(){
		return this.totVolgendeHalte;
	}

	public String getBusId(){
		return this.busID;
	}


	
	public void setbusID(int starttijd){
		this.busID=starttijd+lijn.name()+richting;
	}
	
	public void setTotVolgendeHalte(){
		Positie volgendeHalte = lijn.getHalte(halteNummer+richting).getPositie();
		totVolgendeHalte = lijn.getHalte(halteNummer).afstand(volgendeHalte);
	}
	
	public void halteBereikt(){
		halteNummer+=richting;
		bijHalte=true;
		boolean eindpunt = isEindPuntBereikt();
		printBusInfo(eindpunt);
		if(!eindpunt){
			setTotVolgendeHalte();
		}
	}

	public void printBusInfo(boolean eindpunt){
		if (eindpunt) {
			System.out.printf("Bus %s heeft eindpunt (halte %s, richting %d) bereikt.%n",
					lijn.name(), lijn.getHalte(halteNummer), lijn.getRichting(halteNummer) * richting);
		}
		else {
			System.out.printf("Bus %s heeft halte %s, richting %d bereikt.%n",
					lijn.name(), lijn.getHalte(halteNummer), lijn.getRichting(halteNummer) * richting);
		}

	}
	
	public void start() {
		halteNummer = (richting==1) ? 0 : lijn.getLengte()-1;
		System.out.printf("Bus %s is vertrokken van halte %s in richting %d.%n", 
				lijn.name(), lijn.getHalte(halteNummer), lijn.getRichting(halteNummer)*richting);		
		setTotVolgendeHalte();
	}
	
	public void move(){
		bijHalte=false;
		if (halteNummer == -1) {
			start();
		}
		else {
			totVolgendeHalte--;
		}
	}

	public boolean isEindPuntBereikt(){
		return (halteNummer >= lijn.getLengte() - 1) || (halteNummer == 0);
	}
	

}
