public class Subsekvens {

    private int antForekomster;
    public final String subsekvens;

    public Subsekvens(String ss, int aF) {
        this.antForekomster = aF;
        this.subsekvens = ss;
    }

    // Disse to trengs for naar det skal flettes slik at vi kan
    // oppdatere hvor ofte de forekommer totalt
    public int hentForekomst() {
        return this.antForekomster;
    }
    public void plussForekomst(int x) {
        this.antForekomster += x;
    }
    public String hentSub() {return this.subsekvens;}

    // Lat toString() for testingens skyld
    public String toString() {
        return String.format("(%s,%s)", this.subsekvens, this.antForekomster);
    }
}
