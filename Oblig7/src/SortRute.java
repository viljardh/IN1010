public class SortRute extends Rute {

    // Trenger ikke gjoere stort her, den faar sin egen
    // toString() men ingen finn() fordi den skal ikke
    // ha muligheten til aa navigeres gjennom eller videre.

    SortRute (int rad, int kol) {
        super(rad, kol);
    }
    @Override
    public String toString() {
        return "███";
    }
}
