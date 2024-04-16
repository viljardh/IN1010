// Superklasse for resepter hvor alle egenskaper
// (sans en, vi kommer dit)
// skal arves videre av subklasser og subsubklasser.
// Lages abstrakt, fordi vi vil ikke trenge deklarere en
// uten litt tilleggsinfo.

public abstract class Resept {

    int pasientId, reit;
    Lege utskrivendeLege;
    Legemiddel legemiddel;
    static int teller = 0;
    public final int id;

    Resept (Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;
        this.id = teller++;
    }

    public int hentId() {
        return id;
    }
    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }
    public Lege hentLege() {
        return utskrivendeLege;
    }
    public int hentPasientId() {
        return pasientId;
    }
    public int hentReit() {
        return reit;
    }

    // Liten test soerger for at resepten fortsatt er brukbar
    // hvis den har noen bruk igjen.
    public boolean bruk() {
        if (reit > 0) {
            reit--;
            return true;
        } else {
            return false;
        }
    }

    public abstract String farge();
    public abstract int prisAaBetale();

    // Forvarsel - Jeg kommer til aa bruke instansvariable i toString() fremfor
    // metoder. Orker ikke akkurat naa.
    public abstract String toString();

}
