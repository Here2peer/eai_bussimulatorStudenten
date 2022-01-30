package bussimulator;
import Tijdtools.TijdFuncties;


import static bussimulator.Bussen.*;


public class Runner implements Runnable {


	private static int interval=1000;
	private static int syncInterval=5;

	@Override
	public void run() {

		int tijd=0;
		int counter=0;
		TijdFuncties tijdFuncties = new TijdFuncties();
		tijdFuncties.initSimulatorTijden(interval,syncInterval);
		int volgende = initBussen();
		while ((volgende>=0) || !actieveBussen.isEmpty()) {
			counter=tijdFuncties.getCounter();
			tijd=tijdFuncties.getTijdCounter();
			System.out.println("De tijd is:" + tijdFuncties.getSimulatorWeergaveTijd());
			volgende = (counter==volgende) ? startBussen(counter) : volgende;
			moveBussen(tijd);
			sendETAs(tijd);
			try {
				tijdFuncties.simulatorStep();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
