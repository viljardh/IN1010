import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint {

    Rute[][] lab; // Rutenett av ruter
    int rad, kol; // Kult aa vite hvor stort det er

    @Override
    public String toString() {
        // Ok her er det mye rot for formateringen sin skyld
        // men jeg ville det skulle se pent ut, saa brukte
        // den begrensede kapasiteten jeg har akkurat naa til
        // aa presentere litt fremfor aa loese hele oppgaven.

        // Printer kolonnekoordinater oeverst slik at det blir lettere
        // for bruker aa navigere i labyrinten
        String out = "0  "; // string som skal ha selve labyrinten
        System.out.print("   ");
        for (int j = 0; j < kol; j++) {
            if (j < 10) {
                System.out.print(" " + j + " ");
            } else {
                System.out.print(j + " ");
            }
        }
        System.out.println();

        // Legger selve labyrinten til out-stringen ved aa hente
        // toString() fra ruteobjektene
        for (int i = 0; i < rad; i++) {
            for (int j = 0; j < kol; j++) {
                out += lab[i][j];
            }
            if (i < rad -1) {
                out += "\n"; // Linjeskift naar enden er naadd
                // Pluss litt formateringstjafs
                if ( i < 9) {
                    out += i + 1 + "  ";
                } else {
                    out += i + 1 + " ";
                }
            }
        }
        return out; // Returnerer strengen som inneholder hele labyrinten
        // ja det er stygt med blanding av print, println og en streng, vet.
    }

    // it's a surprise tool that will help us later
    public Rute hentRute(int rad, int kol) {
        return lab[rad][kol];
    }

    // hvor moroa starter
    public void finnUtveiFra(int rad, int kol) {
        Rute start = lab[rad][kol];
        start.finn(null);
    }

    // Leser inn filen paa formatet gitt slik at vi kan lage et labyrintobjekt
    public void lesInn(String fnvn) throws FileNotFoundException {
        File f = new File(fnvn);
        Scanner scan = new Scanner(f);

        // Foerste rekken er tallene som angir stoerrelsen paa array
        String[] str = scan.nextLine().split(" ");
        this.rad = Integer.parseInt(str[0]);
        this.kol = Integer.parseInt(str[1]);
        lab = new Rute[rad][kol]; // Lager labyrintobjektet

        for (int i = 0; i < rad; i++) { // Gaar gjennom filen og leser inn karakterer
            String line = scan.nextLine();
            for (int j = 0; j < kol; j++) {
                if (line.charAt(j) == '.') {
                    // Sjekke om det er en aapning om den er hvit og ligger langs kanten
                    if (j == 0 || i == 0 || i == rad - 1 || j == kol - 1) {
                        lab[i][j] = new Aapning(i, j);
                    } else {
                        // eller bare hvit hvis ikke
                        lab[i][j] = new HvitRute(i, j);
                    }
                } else if (line.charAt(j) == '#'){
                    lab[i][j] = new SortRute(i, j);
                } else {
                    System.out.println("Filformatet stemmer ikke");
                    System.exit(0);
                    // Siden oppgaven ba om det.
                }
            }
        }

        // Inspirert av aa koble celler fra den obligen
        // Maa da vaere en enklere maate aa gjoere dette paa?
        // Gaar gjennom alle rutene, sjekker om den har en
        // nabo og legger den til. Hvis ikke er naboen null.
        for (int i = 0; i < rad; i++) {
            for (int j = 0; j < kol; j++) {
                try {
                    lab[i][j].settNord(lab[i-1][j]);
                } catch (IndexOutOfBoundsException e) {
                    lab[i][j].settNord(null);
                }
                try {
                    lab[i][j].settOest(lab[i][j+1]);
                } catch (IndexOutOfBoundsException e) {
                    lab[i][j].settOest(null);
                }
                try {
                    lab[i][j].settSyd(lab[i+1][j]);
                } catch (IndexOutOfBoundsException e) {
                    lab[i][j].settSyd(null);
                }
                try {
                    lab[i][j].settVest(lab[i][j-1]);
                } catch (IndexOutOfBoundsException e) {
                    lab[i][j].settVest(null);
                }
            }
        }
    }
}
