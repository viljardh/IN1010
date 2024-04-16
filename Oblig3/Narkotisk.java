// Subklasse av legemiddel med ekstra egenskap "styrke"

public class Narkotisk extends Legemiddel {

    public final int styrke;

    Narkotisk (String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public String toString() {
        return String.format("Navn: %s\nPris: %s\nVirkestoff: %s\nStyrke: %s", navn, pris, virkestoff, styrke);
    }
}