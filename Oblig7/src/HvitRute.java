public class HvitRute extends Rute {

    HvitRute (int rad, int kol) {
        super(rad, kol);
    }

    // Ber alle naboene lete videre saaframt det ikke er den
    // vi kom fra eller ikke er null, altsaa langs kanten.
    public void finn(Rute fra) {
        if (fra != nord && nord != null) {
            nord.finn(this);
        }
        if (fra != oest && oest != null) {
            oest.finn(this);
        }
        if (fra != syd && syd != null) {
            syd.finn(this);
        }
        if (fra != vest && vest != null) {
            vest.finn(this);
        }
    }
    @Override
    public String toString() {
        return " ◦ ";
    }

}
