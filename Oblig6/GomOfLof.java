import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Paa forhand beklager jeg det du er i ferd med aa bla gjennom
// men har vaert og kommer til aa vaere sinnssykt opptatt med alt annet
// i livet naa saa dette ble fort og gaeli.
// Veldig gaeli.

public class GomOfLof {

    // Lager vinduer og paneler slik at vi har noe aa jobbe med
    static JFrame vindu = new JFrame("Game of Life");
    static JPanel panel = new JPanel();

    // Hoerer at Stein gikk gjennom noe enklere i andre forelesning om GUI.
    // Naa faar vi alle lide.
    // Deklarerer rakkelet statisk slik at oppdateringsmetoden jeg ogsaa gjorde
    // statisk av en eller annen grunn (jeg ville ikke skrive den to ganger)
    // og lager et skikkelig rot.
    static JPanel knappenett = new JPanel();
    static JPanel rutenett = new JPanel();
    static Verden v;
    static boolean ogre = true; // It's ogre
    static boolean pause = false; // For aa "pause" traaden senere
    static JButton levPanel = new JButton(); // Litt penere representasjon
    static JButton genPanel = new JButton(); // av generasjon og ant levende celler
    static int genNr, antLev;

    // GAMMEL KOMMENTAR:
    // Kjoett og poteter av hva som foregaar her, men det er noe veldig rart.
    // Knappene virker bare paa partallsgenerasjoner, og jeg har ikke en fjerneste
    // ide om hvorfor det kan vaere.
    private static void oppdaterVindu() {
        // Gaar gjennom hele rutenettet av celler
        for (int i = 0; i < v.rutenett.antRader; i++) {
            for (int j = 0; j < v.rutenett.antKolonner; j++) {
                // Henter frem cellen
                Celle c = v.rutenett.hentCelle(i, j);
                // Lager en knapp av den
                c.initGUI();
                c.setPreferredSize(new Dimension(20, 20));
                // Setter den hvit hvis levende, svart hvis doed
                if (c.levende){
                    c.setBackground(Color.white);
                } else {
                    c.setBackground(Color.black);
                }
                // Og legger til i rutenettet.
                rutenett.add(c);
            }
        }

        // Oppdaterer knappene med generasjonsnummer og antall levende
        genNr = v.genNr;
        antLev = v.rutenett.antallLevende();
        genPanel.setText("Generasjon: " + genNr);
        levPanel.setText("Antall levende: " + antLev);
    }

    //NY METODE SOM FIKSER PROBLEMET - Tydeligvis ble det troebbel da
    // gui ble initialisert paa nytt, naa bare blar den gjennom og oppdaterer
    // status. Igjen beklager rotet. Men den funker, og jeg orker ikke rydde akkurat naa.
    private static void oppdaterVindu2() {
        for (int i = 0; i < v.rutenett.antRader; i++) {
            for (int j = 0; j < v.rutenett.antKolonner; j++) {
                Celle c = v.rutenett.hentCelle(i, j);
                if (c.levende){
                    c.setBackground(Color.white);
                } else {
                    c.setBackground(Color.black);
                }
                rutenett.add(c);
            }
        }
        genNr = v.genNr;
        antLev = v.rutenett.antallLevende();
        genPanel.setText("Generasjon: " + genNr);
        levPanel.setText("Antall levende: " + antLev);
    }

    public static void main(String[] args){

        // Kunne sikkert laget denne statisk ogsaa bare for aa vaere tverr.
        // men nei. Denne brukes til aa oppdatere det du ser i vinduet .
        class oppdater extends Thread {
            @Override
            public void run() {
                try {
                    // Mens traaden er i live
                    while (!ogre) {
                        // Og pauset
                        if (pause) {
                            // Skal bare vinduet vises, og ingenting oppdateres
                            vindu.setVisible(true);
                        } else {
                            // Ellers skal alt oppdateres annethvert sekund
                            // gjorde det slik saann at pausingen oppleves
                            // litt smothere
                            sleep(1000);
                            // Ny oppdateringsmetode som ikke reinitaliserer cellene som knapper
                            v.oppdatering();
                            oppdaterVindu2();
                            sleep(1000);
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        }

        // Lager oppdateringstraaden
        oppdater o = new oppdater();

        // Lager knapper for start/pause og avslutting
        JButton pausKnapp = new JButton("Start/Pause");
        JButton stoppKnapp = new JButton("Avslutt");

        pausKnapp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hvis traaden ikke er startet sparker vi den igang
                if (ogre && !o.isAlive()) {
                    ogre = false;
                    o.start();
                } else {
                    // Hvis den er startet pauser vi den
                    pause = !pause;
                }
            }
        });
        stoppKnapp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kverker traaden og scriptet naar den trykkes
                ogre = true;
                System.exit(0);
            }
        });

        // Maa gi de en verdi for aa kunne kompilere
        int rad  = 0;
        int kol  = 0;
        try {
            rad = Integer.parseInt(args[0]);
            kol = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.out.println("Du maa oppgi rad og kolonner i argument");
            System.out.println("Eks: java GomOfLof 10 10");
        }

        // genererer verden
        v = new Verden(rad, kol);

        // Mekker litt i vinduet
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e){
            System.out.println("    ");
        }

        vindu.add(panel);

        // Proever at knappene ser litt ryddige ut i det minste
        knappenett.setLayout(new GridLayout(2, 2));
        knappenett.add(pausKnapp);
        knappenett.add(stoppKnapp);
        knappenett.add(levPanel);
        knappenett.add(genPanel);

        // Try catch for aa kunne gi feilmelding hvis bruker ikke oppga
        // args for rader og kolonner
        try {
            rutenett.setLayout(new GridLayout(kol, rad));
        } catch (Exception e) {
            System.out.print("");
            System.exit(1);
        }

        // Verden er generert, vi oppdaterer vinduet med
        // den gamle metoden som tilegner knappen helt på nytt
        oppdaterVindu();

        // Slenger paa knappene og rutenetet av celler
        panel.add(knappenett);
        panel.add(rutenett);

        // Maa til
        vindu.pack();
        vindu.setVisible(true);

    }
}

// jeg savner python
