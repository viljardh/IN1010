import java.util.Scanner;

// kan nesten ikke tro jeg gledet meg til å skrive main() igjen
public class GameOfLife {
    public static void main(String[] args){
        
        // scannerobjekt sånn at vi får litt brukerinput
        Scanner input = new Scanner(System.in);

        // håper bruker har koll på hva som er rader og kolonner
        // i tilfelle de blander og får en veldig bred en mens 
        // de ville ha en høy en. 
        System.out.println("Hvor mange rader vil du ha?");
        int rad = input.nextInt();
        System.out.println("Hvor mange kolonner vil du ha?");
        int kol = input.nextInt();

        // genererer verden og tegner den
        Verden v = new Verden(rad, kol);
        v.tegn();

        // dette var litt rart. hvis ikke jeg hadde med denne gikk den
        // direkte videre med ny generasjon, selv om du kun trykket inn
        // rad/kol uten å gå videre. Lurer på om den registrerer når du 
        // trykker enter for å punche inn siste tall.
        String cont = input.nextLine();

        // gir bruker mulighet til å fortsette eller avslutte basert på input
        Boolean x = true;
        while (x) {
            System.out.println("Trykk enter for å fortsette eller q for å avslutte");
            cont = input.nextLine();
            if (cont.equals("q")) {
                System.out.println("Game Of Life avsluttes, takk for nå!");
                input.close();
                x = false;
            }
            else {
                v.oppdatering();
                v.tegn();
            }
        }
    }
}

// jeg savner python
