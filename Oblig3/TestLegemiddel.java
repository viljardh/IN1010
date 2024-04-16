// Testklasse

public class TestLegemiddel {

    // Testklasse som lar oss skrive inn hva vi tester og testen vi ønsker
    // aa utføre. Hvis testen feiler sier den hva og avslutter, ellers er alt ok.
    private static void testLegemiddel(String test, boolean virker) {
        if (!virker) {
            System.out.println("Test for " + test + " feilet.");
            System.exit(1);
        }
        else {
            System.out.println("Test for " + test + " virker!");
        }
    }

    // Deklarerer statiske objekter sånn at ID-funksjonen i konstruktøren slår ut
    static Narkotisk lmNark = new Narkotisk("testNark", 123, 123, 9);
    static Vanedannende lmVane = new Vanedannende("testVane", 123, 123, 4);
    static Vanlig lmVanlig = new Vanlig("testVanlig", 123, 123);

    // Testmetoder hvor jeg tester ID pluss litt vilkårlig fra de forskjellige klassene
    private static void testNarkotisk() {
        testLegemiddel("Narkotisk - Virkestoff", lmNark.virkestoff == 123);
        testLegemiddel("Narkotisk - ID", lmNark.id == 0);
        System.out.println(lmNark);
    }

    private static void testVanedannende() {
        testLegemiddel("Vanedannende - ID", lmVane.id == 1);
        testLegemiddel("Vanedannende - Styrke", lmVane.styrke == 4);
        System.out.println(lmVane);
    }

    private static void testVanlig() {
        testLegemiddel("Vanlig - ID", lmVanlig.id == 2);
        testLegemiddel("Vanlig - Pris", lmVanlig.hentPris() == 123);
        lmVanlig.settNyPris(555);
        testLegemiddel("Vanlig - Oppdatert pris", lmVanlig.hentPris() == 555);
        System.out.println(lmVanlig);
    }

    // Main-metode som kjører testmetodene
    public static void main(String[] args) {
        testNarkotisk();
        testVanedannende();
        testVanlig();
    }

}
