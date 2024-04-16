import java.util.HashMap;
import java.util.ArrayList;

public class FletteTrad implements Runnable{

    Monitor2 m;
    HashMap<String, Subsekvens> h1, h2, h3;
    // Tar inn monitor som holder styr paa registeret igjen
    FletteTrad (Monitor2 m) {
        this.m = m;
    }
    @Override
    public void run () {
        // Her tror jeg at jeg har provd omtrent alt, og dene funker veldig paalitelig.
        // Hvis jeg har kjoert veldig mange ganger/PC-en har vaert paa lenge misser
        // den noen ganger siste flett, vet at while-loekka ikke er ideell.
        // Mistenker feilen kan oppstaa naar for mange traader dauer fordi en annen
        // traad sitter paa en ting som skal inn i monitoren, og stoerrelsen er < 1
        // noen sykler for lenge
        try {
            // Countdownlatch og tellere fikk jeg faen ikke til aa funke
            // men dette kombinert med join gir meg rundt 995/1000.
            // Og ja. Jeg har testet.
            while (m.storr() > 1) {
                // Endret metoden i monitoren slik at den henter ut en liten
                // array med to hashmaps slik at to strings ikke staar med hver
                // sin hashmap den ikke faar flettet
                ArrayList<HashMap<String, Subsekvens>> hent = m.hentUt();
                h1 = hent.get(0);
                h2 = hent.get(1);
                // Bruker den statiske metoden slik at vi slipper aa vente
                // paa aa flette
                h3 = SubsekvensRegister.slaaSammen(h1, h2);
                m.settInn(h3);
                //m.tellNed(); // Liket av ideer jeg ikke fikk til aa funke
            }
        } catch(NullPointerException e){
            System.out.print("");
            // Ikke spoer.
        }
    }
}
