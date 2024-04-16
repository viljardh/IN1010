// Subklasse av Legemiddel uten ekstra egenskaper

public class Vanlig extends Legemiddel {

    Vanlig(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }

    public String toString() {
        return String.format("Navn: %s\nPris: %s\nVirkestoff: %s", navn, pris, virkestoff);
    }
}

