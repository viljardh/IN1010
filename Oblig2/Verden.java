public class Verden {

    // deklarerer nødvendige variable
    public int genNr;
    public Rutenett rutenett;

    // konstruerer verden ved å lage et rutenett, fylle det med celler
    // 1/3 av hvis er i live, og koble de sammen. 
    public Verden(int rad, int kol) {
        rutenett = new Rutenett(rad, kol);
        genNr = 0;
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
    }    
    
    // metode for å tegne rutenettet pluss litt info
    public void tegn() {
        rutenett.tegnRutenett();
        System.out.println("Generasjon: " + genNr + " - Antall levende celler: " + rutenett.antallLevende());
    }

    // her kavet jeg lenge, fordi da jeg begynte deklarerte jeg bare 
    // int rad/kol uten å tilegne de noe og brukte de som begrensinger i for-løkka,
    // trodde det kom av seg selv når du konstruerer rutenettet med rad,kol som argumenter. 
    // Skjønte ikke hvorfor driten ikke ville oppdatere seg.
    // Men ja, Ctrl-V løkkene som itererer over hele rutenettet i to omganger -
    // Først sånn at alle cellene vet hvor mange levende naboer de har,
    // og deretter for å oppdatere cellenes status basert på den informasjonen. 
    public void oppdatering() {
        for (int i = 0; i < rutenett.antRader; i++) {
            for (int j = 0; j < rutenett.antKolonner; j++) {
                rutenett.hentCelle(i,j).tellLevendeNaboer();
            }
        }
        for (int i = 0; i < rutenett.antRader; i++) {
            for (int j = 0; j < rutenett.antKolonner; j++) {
                rutenett.hentCelle(i,j).oppdaterStatus();
            }
        }
        genNr++;

    }
}
