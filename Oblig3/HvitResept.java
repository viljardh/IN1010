// Subklasse av resept uten ekstra egenskaper

public class HvitResept extends Resept  {

    HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    public String farge() {
        return "hvit";
    }
    public int prisAaBetale() {
        return legemiddel.hentPris();
    }
    public String toString() {
        return String.format("Legemiddel: %s\nUtskrevet av: %s\nFor pasient: %s\nGjenstaaende uttak: %S",
                legemiddel.navn, utskrivendeLege.navn, pasientId, reit);
    }
}
