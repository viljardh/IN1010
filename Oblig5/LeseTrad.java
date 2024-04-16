import java.io.FileNotFoundException;

public class LeseTrad  implements Runnable {

    Monitor2 m;
    String fnvn;

    // Bruker monitor som medium til aa holde styr paa registeret
    // felles for alle traader
    public LeseTrad(String fnvn, Monitor2 m) {
        this.m = m;
        this.fnvn = fnvn;
    }

    @Override
    public void run() {
        // Lages en traad per fil som skal leses inn, saa de trenger bare kjoere
        // den ene gangen. Sikkert en bra loesning det.
        try {
            m.settInn(SubsekvensRegister.lesInn(fnvn));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
