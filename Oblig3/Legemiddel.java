// Lager en superklasse legemiddel hvor alle egenskapene de
// forskjellige legemidlene har til felles kan lagres og
// arves av andre klasser. Lager den abstrakt med metoder
// alle

public abstract class Legemiddel {

    String navn;
    int pris;
    static int teller = 0;
    public final int id;
    public final double virkestoff;

    Legemiddel (String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.id = teller++;
        this.pris = pris;
        this.virkestoff = virkestoff;
        }

    public int hentPris() {
        return pris;
    }
    public void settNyPris(int nyPris) {
        pris = nyPris;
    }
    public abstract String toString();

}