import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig5Hele {
    public static void main(String[] args) throws FileNotFoundException {

        // ait
        int antTraa = 8;

        // deklarere to av det meste
        SubsekvensRegister subRegFrisk = new SubsekvensRegister();
        SubsekvensRegister subRegSyk = new SubsekvensRegister();
        ArrayList<String> friskList = new ArrayList<>();
        ArrayList<String> sykList = new ArrayList<>();

        // Lese mappenavn som input og smekke veien til metadata som en string
        String path = args[0];
        String fnvn = "metadata.csv";
        // Sende til filleser og scanner
        File input = new File(path + fnvn);
        Scanner scan = new Scanner(input);
        while (scan.hasNextLine()) {
            // Blar gjennom linjene, sender til hver sin liste avhengige av om
            // de har vaert smittet eller ikke. Full pathnavn til filene fra
            // mappen main kjoeres i
            String[] line = scan.nextLine().split(",");
            if (line[1].equals("False")){
                friskList.add(path+line[0]);
            } else {
                sykList.add(path+line[0]);
            }
        }
        scan.close(); // Shoutouts til Anna

        // Deklarere to av det meste igjen, en for friske og en for syke

        // Synes det er penere aa deklarere int for lengder og slikt
        // Trengs for aa iterere gjennom filnavn og for aa gi oss ant
        // traader for lesing. Skulle gjerne brukt til countdown ogsaa,
        // men det blir i mitt neste liv.
        int lenFrisk = friskList.size();
        int lenSyk = sykList.size();

        // Monitorer med pekere til sine respektive subsekvensregistre
        Monitor2 mFrisk = new Monitor2(subRegFrisk);
        Monitor2 mSyk = new Monitor2(subRegSyk);

        // Lesetraader med lengde antall filer
        // Flettetraader med lengde aatte
        Thread[] lesFriskTraa = new Thread[lenFrisk];
        Thread[] flettFriskTraa = new Thread[antTraa];
        Thread[] lesSykTraa = new Thread[lenSyk];
        Thread[] flettSykTraa = new Thread[antTraa];

        // Itererer over listene filnavn, starter traader som leser informasjonen
        // til hver sitt subsekvensregister
        for (int i = 0; i < lenFrisk; i++) {
            lesFriskTraa[i] = new Thread(new LeseTrad(friskList.get(i), mFrisk));
            lesFriskTraa[i].start();
        }
        for (int i = 0; i < lenSyk; i++) {
            lesSykTraa[i] = new Thread(new LeseTrad(sykList.get(i), mSyk));
            lesSykTraa[i].start();
        }

        // Joiner de saann at naar vi skal flette kan vi ta utgangspunkt i at
        // det ikke vil komme flere (som gjoer det litt enklere, jeg har
        // stanget lenge nok med aa faa dette til aa funke, langt mindre
        // ordentlig parallelt.
        for (Thread thread : lesFriskTraa) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        for (Thread thread : lesSykTraa) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }

        // Gjentar prosessen for flettetraadene
        for (int i = 0; i < antTraa; i++) {
            flettFriskTraa[i] = new Thread(new FletteTrad(mFrisk));
            flettFriskTraa[i].start();
        }
        for (int i = 0; i < antTraa; i++) {
            flettSykTraa[i] = new Thread(new FletteTrad(mSyk));
            flettSykTraa[i].start();
        }

        // Etter utallelige timer krangling med countdownlatch, tellere opp
        // og ned etc etc var dette eneste maate aa gjoere det paa.
        // Et problem jeg mistenker kan oppstaa er at alle traadene
        // er doede foer siste flett er ferdig, men den er i saa fall
        // uhyre sjelden - Les FletteTrad.java
        for (Thread t : flettFriskTraa) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        for (Thread t : flettSykTraa) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }

        // Deklarere noen hashmaps for aa gjoere sammenligningen av
        // sekvenser litt ryddigere
        HashMap<String, Subsekvens> flettetFrisk, flettetSyk, alkjdsjf;
        flettetFrisk = subRegFrisk.taUt();
        flettetSyk = subRegSyk.taUt();
        alkjdsjf = new HashMap<>(); // Sorry, lei naa

        for (String s : flettetSyk.keySet()) {
            // Blar bare gjennom syke fordi iflg oppgteksten er det de vi er
            // mest interesserte i
            if (flettetFrisk.containsKey(s)) {
                // Hvis sekvensen finnes i friske sekvenser henter vi
                // forekomstene slik at vi kan sammenligne
                int antF = flettetFrisk.get(s).hentForekomst();
                int antS = flettetSyk.get(s).hentForekomst();
                // Hvis de eksisterer og forekommer sju ganger eller fler
                if (antS >= antF + 7) {
                    // Legger vi de til
                    alkjdsjf.put(s, flettetSyk.get(s));
                }
                // Hvis de ikke eksisterer blant friske, men har dukket opp
                // sju ganger eller mer hos smittede, legger vi den til.
            } else if (flettetSyk.get(s).hentForekomst() >= 7) {
                alkjdsjf.put(s, flettetSyk.get(s));
            }
        }

        // Presentere pent
        System.out.println("Dominante subsekvenser:");
        for (String s : alkjdsjf.keySet()) {
            System.out.println(s + " forekommer " +
                    alkjdsjf.get(s).hentForekomst() + " ganger.");
        }
    }
}
