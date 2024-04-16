// Subklasse av hvit resept, har egen egenskap at de alltid
// kommer med 3x reits, og er alltid gratis. Trenger ikke
// deklarere den med alle parametre selv om den er sub av en
// super som krever det, saa lenge vi legger noe i super.

public class MilitaerResept extends HvitResept {

    MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId) {
        super(legemiddel, utskrivendeLege, pasientId, 3);
        legemiddel.settNyPris(0);
    }

    public String toString() {
        return String.format("Legemiddel: %s\nUtskrevet av: %s\nFor pasient: %s\nGjenstaaende uttak: %S",
                legemiddel.navn, utskrivendeLege.navn, pasientId, reit);
    }
}
