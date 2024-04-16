import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

public class SubsekvensRegister {

    private final ArrayList<HashMap<String, Subsekvens>> hashBeholder = new ArrayList<>();

    public void settInn(HashMap<String, Subsekvens> reg) {
        hashBeholder.add(reg);
    }

    // Fjerner og returnerer hashmap saa lenge det er noe aa hente
    public HashMap<String, Subsekvens> taUt() {
        try {
            return hashBeholder.remove(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public int storr() {
        return hashBeholder.size();
    }

    // Tar filnavn med path fra grunnmappe som parameter og henter alle substrenger av
    // ramsen med strenger, sekvens for sekvens, linje for linje
    public static HashMap<String, Subsekvens> lesInn(String path) throws FileNotFoundException {
        HashMap<String, Subsekvens> subHash = new HashMap<>();
        File input = new File(path);
        Scanner scan = new Scanner(input);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            // Saa lenge det er 3 eller mer tegn i linja kan vi lage en substring
            if (line.length() > 2) {
                // Itererer oss gjennom elementene i linja frem til nest nest siste
                // fordi trenger ingen subsekvenser mindre enn 3 tegn
                for (int i = 0; i < line.length() - 2; i++) {
                    // Henter ut karakteren vi er paa pluss de to neste
                    String substr = line.substring(i, i + 3);
                    // Lager et subsekvensobjekt. Forekomst kun 1 fordi vi ikke
                    // er interesserte i aa telle de ennaa
                    Subsekvens subs = new Subsekvens(substr, 1);
                    // Legger til i HashMapen med substring og subsekvensobjekt
                    subHash.put(substr, subs);
                }
            }
        }
        scan.close();
        return subHash;
    }

    public static HashMap<String, Subsekvens> slaaSammen (HashMap<String, Subsekvens> h1, HashMap<String, Subsekvens> h2) {

        // Deklarerer hashmap som peker paa samme elementene som ene input
        // Unoedvendig, men ryddigere imo
        HashMap<String, Subsekvens> out = new HashMap<>(h1);

        // Siden out naa er kopi av foerste parameter sjekker vi om noen i andre parameter
        // er der fra foer
        for (String s : h2.keySet()) {
           if (out.containsKey(s)) {
               // i saa tilfelle maa vi legge antall forekomstene i parameter 2
               int frkmst = h2.get(s).hentForekomst();
               // til parameter 1
               out.get(s).plussForekomst(frkmst);
           } else {
               // Ellers bare smekker vi den paa plass.
               out.put(s, h2.get(s));
           }
        }
        return out;
    }

    // Standard skuring for aa finne stoerste element
    // Maa bare hente skiten ut, ligger godt begravet i
    // arraylistobjektet.
    public Subsekvens hentStorst() {
        try {
            Subsekvens stoerst = null;
            int stoerstForekomst = 0;
            HashMap<String, Subsekvens> hM = hashBeholder.get(0);
            for (String s : hM.keySet()) {
                int c = hM.get(s).hentForekomst();
                if (c > stoerstForekomst) {
                    stoerstForekomst = c;
                    stoerst = hM.get(s);
                }
            }
            return stoerst;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    // Lat toString() fordi eneste jeg er interessert i aa bruke den er
    // for underveistesting naar flettingen er ferdig
    public String toString() {
        return hashBeholder.get(0).toString();
    }
}