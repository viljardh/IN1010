public class Integrasjonstest {
    public static void main(String[] args) {

        // Deklarerer et par legemidler for innlevingens skyld.
        Narkotisk ritalin = new Narkotisk("Ritalin", 357, 154929, 5);
        Vanedannende xanax = new Vanedannende("Xanax", 176, 134809, 5);
        Vanlig microgynon = new Vanlig("Microgynon", 84, 595504);
        Vanlig aerius = new Vanlig("Aerius", 240, 97080);

        Lege hus = new Lege("Dr Hus");
        Spesialist hytte = new Spesialist("Dr Hytte", "OOAYDS27");

        // Ja, ja, vet at milresept er extension av hvit resept og at
        // ritalin kun faas paa blaa, men det er mange vitser rundt
        // soldater som faar amfetaminer.
        Presept pResept = new Presept(microgynon, hus, 123, 3);
        MilitaerResept milResept = new MilitaerResept(ritalin, hytte, 234);
        HvitResept hvResept = new HvitResept(aerius, hus, 345, 3);
        BlaaResept blResept = new BlaaResept(xanax, hytte, 456, 4);

        // En ting jeg merket. Siden tingene på de forskjellige reseptene
        // endrer seg i pris, og reseptene tar pekere som parametre endrer
        // prisen seg i selve objektet ogsaa naar toString() kalles.
        // Ikke noedvendigvis helt ideelt, men i traad med oppgaven.
        System.out.println(ritalin);
        System.out.println(xanax);
        System.out.println(microgynon);
        System.out.println(aerius);
        System.out.println(hus);
        System.out.println(hytte);
        System.out.println(pResept);
        System.out.println(milResept);
        System.out.println(hvResept);
        System.out.println(blResept);
    }
}
