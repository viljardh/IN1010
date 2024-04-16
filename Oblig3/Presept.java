// Subklase av hvit resept igjen, bare med en sjekk som trekker fra
// 108 kroner, med mindre legemiddelet koster mindre enn 108.
// Da er det gratis. Saann som jeg forstaar det.

public class Presept extends HvitResept {

    Presept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);

        int pris = legemiddel.hentPris();
        if (pris >= 108) {
            legemiddel.settNyPris(pris - 108);
        } else {
            legemiddel.settNyPris(0);
        }
    }
    public String toString() {
        return String.format("Legemiddel: %s\nUtskrevet av: %s\nFor pasient: %s\nGjenstaaende uttak: %S",
                legemiddel.navn, utskrivendeLege.navn, pasientId, reit);
    }
}
