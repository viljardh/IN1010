// Subklasse for leger med interfacen godkjenningsfritak

public class Spesialist extends Lege implements Godkjenningsfritak {

    String kontrollkode;

    Spesialist(String navn, String kontrollkode) {
        super(navn);
        this.kontrollkode = kontrollkode;
    }

    public String hentKontrollkode() {
        return kontrollkode;
    }

    public String toString() {
        return "Navn: " + navn;
    }
}
