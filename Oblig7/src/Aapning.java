public class Aapning extends HvitRute {

    Aapning (int rad, int kol) {
        super(rad, kol);
    }

    // Omsider har vi funnet en aapning, og melder ifra om det og hvor den er.
    public void finn(Rute fra) {
        System.out.println("Aapning rad " + rad + " kol " + kol);
    }

}
