public class Celle {
    
    // deklarerer litt variable som skal brukes i klassen
    // og muligens andre klasser, derfor public
    public boolean levende;
    public Celle[] naboer;
    public int antNaboer, antLevendeNaboer;

    // konstruktor for en celle, kommer til verden død og
    // med opp til åtte naboer
    public Celle() {
        levende = false;
        naboer = new Celle[8];
        antNaboer = 0;
        antLevendeNaboer = 0;
    }

    // metode for å sjekke om cellen lever, returnerer true/false
    public boolean erLevende() {
        return levende;
    }

    // metoder som oppdaterer cellens status som levende eller død
    public void settDoed() {
        levende = false;
    }
    public void settLevende() {
        levende = true;
    }

    // henter ascii-karakter avhengig av om cellen lever eller ei
    public char hentStatusTegn() {
        if (levende) {
            return('O');
        }
        else {
            return('.');
        }
    }

    // sørger for at det er ledig plass i listen, og legger til 
    // et celleobjekt i listen over naboer.
    public void leggTilNabo(Celle nabo) {
        if (antNaboer < 8) {
            naboer[antNaboer] = nabo;
            antNaboer++;
        }
    }

    // går gjennom nabolisten, sjekker om det er en nabo der
    // og om den lever.
    public void tellLevendeNaboer() {
        antLevendeNaboer = 0;
        for (int i = 0; i < 8; i++) {
            if (naboer[i] != null && naboer[i].erLevende()){
                antLevendeNaboer++;
                
            }
        }
    }

    // sjekker hvorvidt cellen har for mange naboer til  å leve
    // eller perfekt antall til å komme til live, basert på GoL-regler.
    public void oppdaterStatus() {
        if (levende) {
            if (antLevendeNaboer < 2 || antLevendeNaboer > 3) {
                settDoed();
            }            
        }
        else {
            if (antLevendeNaboer == 3) {
                settLevende();
            }
        }
    }
}
