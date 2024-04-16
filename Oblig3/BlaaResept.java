// Blaaresepter har et par andre genskaper, saann som at vi maa senke prisen litt.
// Maa konvertere litt mellom datatyper. Ikke pent, men brukbart.

public class BlaaResept extends Resept {

    BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    public String farge() {
        return "blaa";
    }

    public int prisAaBetale() {
        float pris = legemiddel.hentPris();
        return Math.round(pris/4);
    }
    public String toString() {
        return String.format("Legemiddel: %s\nUtskrevet av: %s\nFor pasient: %s\nGjenstaaende uttak: %S",
                legemiddel.navn, utskrivendeLege.navn, pasientId, reit);
    }
}