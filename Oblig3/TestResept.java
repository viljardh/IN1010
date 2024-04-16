// Testklasse for resept. Mye det samme som i TestLegemiddel.

public class TestResept {

    private static void testResept(String test, boolean virker) {
        if (!virker) {
            System.out.println("Test for " + test + " feilet.");
            System.exit(1);
        }
        else {
            System.out.println("Test for " + test + " virker!");
        }
    }

    static Narkotisk lmNark = new Narkotisk("testNark", 123, 123, 9);
    static Vanedannende lmVane = new Vanedannende("testVane", 123, 123, 4);

    static Lege dummyLege = new Lege("Dr Hytte");

    static BlaaResept resBl = new BlaaResept(lmNark, dummyLege, 789, 3);
    static MilitaerResept resMil = new MilitaerResept(lmVane, dummyLege, 789);

    private static void testBl() {
        testResept("Blaaresept - Farge", resBl.farge().equals("blaa"));
        testResept("Blaaresept - Pris", resBl.prisAaBetale() == 31);
        System.out.println(resBl);
    }

    private static void testMil() {
        testResept("Militaerresept - Gjenstående bruk", resMil.hentReit() == 3);
        while (resMil.bruk()) {
            resMil.bruk();
        }
        testResept("Militaerresept - Oppbrukt", !resMil.bruk());
        testResept("Militaerresept - Pris", resMil.prisAaBetale() == 0);
        testResept("Militaerresept - ID", resMil.hentId() == 1);
        System.out.println(resMil);
    }

    public static void main(String[] args) {
        testBl();
        testMil();
    }
}
