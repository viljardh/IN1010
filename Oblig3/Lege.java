// "Super"klasse for leger og spesialister.

public class Lege implements Comparable<Lege> {

    String navn;

    public Lege(String navn) {
        this.navn = navn;
    }

    public String toString() {
        return "Navn: " + navn;
    }
    @Override
    public int compareTo(Lege data) {
        return this.navn.compareTo(data.navn);
    }
}

