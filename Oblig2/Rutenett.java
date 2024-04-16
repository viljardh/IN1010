public class Rutenett {

    // deklarerer litt variable som kan komme godt med
    // inkl en todimensjonal liste til Celle-objekter
    public int antRader, antKolonner;
    public Celle[][] rutene;
    public Celle celle;

    // konstruerer rutenettet med rader og kolonner
    // føles grusomt å ha rader/kolonner fremfor kolonner/rader
    // sånn mtp koordinatsystemer, men vOv
    public Rutenett(int rad, int kol) {
        antRader = rad;
        antKolonner = kol;
        rutene = new Celle[rad][kol];
    }

    // lager et Celle-objekt og tilegner den til "celle"
    // og den har 1/3 sjanse til å være levende. Setter i 
    // rutenettet gitt koordinater. 
    public void lagCelle(int rad, int kol) {
        celle = new Celle();
        if (Math.random()<=0.333) {
            celle.settLevende();
        }
        rutene[rad][kol] = celle;
    }

    // metoden går gjennom rutenettet, kolonne for kolonne
    // og rad for rad, og plopper på plass en celle som vi lager
    // via metoden over. 
    public void fyllMedTilfeldigeCeller() {
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                lagCelle(i, j);
            }
        }
    }

    // metode som lar oss returnere et celleobjekt gitt koordinater
    // som input. Hvis koordinatene er "out of bounds" vil dene metoden
    // returnere "null" - dette er for celler som ligger langs kanten og
    // i hjørner. Veldig kjent for når vi skal lage nabolister. 
    public Celle hentCelle(int rad, int kol) {
        if ((rad >= 0 && rad < antRader)) {
            if (kol >=0 && kol < antKolonner) {
                return rutene[rad][kol];
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    // tegner ut alle cellene i rutenettet med symbol avhengig av om 
    // de lever eller ikke. Jeg printer også et ekstra whitespace mellom
    // tegnene, jeg synes det ser bedre ut.
    public void tegnRutenett(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                //celle = hentCelle(i, j);
                System.out.print(" " + hentCelle(i, j).hentStatusTegn());
            }
            System.out.println();
        }
    }

    // for for if if for if for if if for while
    // henter først ut en referanse til et celleobjekt sånn at vi har noe
    // å sjekke naboene til. Så sjekker vi ett hakk over, under, til høyre
    // og venstre. hentCelle-metoden gir oss null hvis den er langs kanten,
    // så sjekker for null-return. Så lenge den ikke gjør det, legger vi 
    // den til i listen over naboer. Og så lenge det ikke er cellen selv. 
    // Fikk bruk for De Morgan's lov fra IN1150 da jeg oversatte dette segmentet
    // fra python-koden jeg laget i fjor:
    // if not (i == 0 and j == 0) er ekvivalent med if (i != 0 or j != 0)
    public void settNaboer(int rad, int kol) {
        celle = hentCelle(rad, kol);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 || j != 0) {
                    if (hentCelle(rad + i, kol + j) != null) {
                        celle.leggTilNabo(hentCelle(rad + i, kol + j));
                    }
                }
            }
        }
    }
    
    // itererer over alle cellene i rutenettet og kjører metoden
    // som gjør at cellene vet hva de har av naboskap. På dette 
    // tidspunktet hadde jeg den nested for-loopen på Ctrl-C/Ctrl-V. 
    public void kobleAlleCeller() {
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                settNaboer(i, j);
                }
        }
    }

    // Ctrl-V nested for-løkke, går over rutenettet og sjekker hvor mange
    // av de som lever så vi har litt info å printe ut når vi skal tegne. 
    // Vet jeg kunne droppet pekeren og bare skrevet
    // "if(hent.Celle(i,j).erLevende()", men synes dette er ryddigere.
    public int antallLevende() {
        int c = 0;
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                celle = hentCelle(i, j);
                if (celle.erLevende()) {
                    c++;
                }
            }
        }
        return c;
    }


}