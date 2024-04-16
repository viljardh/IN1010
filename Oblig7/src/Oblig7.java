import java.io.FileNotFoundException;
import java.util.Scanner;

class Oblig7 {

    public static void main(String[] args) throws FileNotFoundException {

        Labyrint lab = new Labyrint();
        String inn = args[0];
        lab.lesInn("labyrinter/" + inn);
        System.out.println(lab);

        Scanner scan = new Scanner(System.in);

        String inp;

        while (true) {
            try {
                System.out.println();
                System.out.println("'q' for aa avslutte");
                System.out.println("'p' for aa printe labyrinten paa nytt");
                System.out.println();
                System.out.println("Skriv inn koordinater [rad] [kolonne]");

                inp = scan.nextLine();
                if (inp.equals("q")) {
                    System.out.println("Avslutter");
                    break;
                }
                if (inp.equals("p")) {
                    System.out.println(lab);
                    continue;
                }
                String[] koord = inp.split(" ");
                int rad = Integer.parseInt(koord[0]);
                int kol = Integer.parseInt(koord[1]);
                try {
                    if (lab.hentRute(rad, kol) instanceof SortRute) {
                        System.out.println("Kan ikke starte i sort rute");
                    } else {
                        lab.finnUtveiFra(rad, kol);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Vennligst oppgi gyldige koordinater");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vennligst bruk gyldig input");
            }
        }

        scan.close();
    }

}
    