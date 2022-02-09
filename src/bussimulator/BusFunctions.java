package bussimulator;

import com.thoughtworks.xstream.XStream;

public class BusFunctions {

    public static void sendETAs(int nu, Bus bus){
        int i=0;
        Bericht bericht = new Bericht(bus.getLijn().name(), bus.getBedrijf().name(), bus.getBusId(), nu);
        if (bus.getBijHalte()) {
            ETA eta = new ETA(bus.getLijn().getHalte(bus.getHalteNummer()).name(),bus.getLijn().getRichting(bus.getHalteNummer())*bus.getRichting(), 0);
            bericht.ETAs.add(eta);
        }
        Halte.Positie eerstVolgende= bus.getLijn().getHalte(bus.getHalteNummer()+bus.getRichting()).getPositie();
        int tijdNaarHalte=bus.getTotVolgendeHalte()+nu;
        for (i = bus.getHalteNummer()+bus.getRichting(); !(i>=bus.getLijn().getLengte()) && !(i < 0); i=i+bus.getRichting()){
            tijdNaarHalte+= bus.getLijn().getHalte(i).afstand(eerstVolgende);
            ETA eta = new ETA(bus.getLijn().getHalte(i).name(), bus.getLijn().getRichting(i)*bus.getRichting(),tijdNaarHalte);
            System.out.println(bericht.lijnNaam + " naar halte" + eta.halteNaam + " t=" + tijdNaarHalte);
            bericht.ETAs.add(eta);
            eerstVolgende=bus.getLijn().getHalte(i).getPositie();
        }
        bericht.eindpunt=bus.getLijn().getHalte(i-bus.getRichting()).name();
        sendBericht(bericht);
    }

    public static void sendLastETA(int nu, Bus bus){
        Bericht bericht = new Bericht(bus.getLijn().name(), bus.getBedrijf().name(),bus.getBusId(),nu);
        String eindpunt = bus.getLijn().getHalte(bus.getHalteNummer()).name();
        ETA eta = new ETA(eindpunt,bus.getLijn().getRichting(bus.getHalteNummer())* bus.getRichting(),0);
        bericht.ETAs.add(eta);
        bericht.eindpunt = eindpunt;
        sendBericht(bericht);
    }

    public static void sendBericht(Bericht bericht){
        XStream xstream = new XStream();
        xstream.alias("Bericht", Bericht.class);
        xstream.alias("ETA", ETA.class);
        String msg = xstream.toXML(bericht);
        SendMessage producer = new SendMessage();
        producer.sendBericht(msg);
    }
}
