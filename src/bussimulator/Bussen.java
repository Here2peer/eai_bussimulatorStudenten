package bussimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

public class Bussen {

    private static HashMap<Integer,ArrayList<Bus>> busStart;
    private static List<Bus> actieveBussen;

    public Bussen(BussenGenerator generator){
        busStart = (HashMap<Integer, ArrayList<Bus>>) generator.getBusStart();
        actieveBussen = generator.getActieveBussen();
    }


    static int startBussen(int tijd){
        for (Bus bus : busStart.get(tijd)){
            actieveBussen.add(bus);
        }
        busStart.remove(tijd);
        return (!busStart.isEmpty()) ? Collections.min(busStart.keySet()) : -1;
    }

    public void moveBussen(int nu){
        Iterator<Bus> itr = actieveBussen.iterator();
        while (itr.hasNext()) {
            Bus bus = itr.next();
            bus.move();
            if(bus.getTotVolgendeHalte() == 0 && bus.isEindPuntBereikt()) {
                BusFunctions.sendLastETA(nu, bus);
                itr.remove();
            }
        }
    }

    public void sendETAs(int nu){
        Iterator<Bus> itr = actieveBussen.iterator();
        while (itr.hasNext()) {
            Bus bus = itr.next();
            BusFunctions.sendETAs(nu, bus);
        }
    }
}
