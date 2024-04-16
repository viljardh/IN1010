abstract class Rute {
    // Skal kun lage subklasser av denne klassen, har en del
    // egenskaper alle skal ha, inkl koordinater og naboer
    int rad, kol;
    Rute nord, oest, syd, vest;

    public Rute (int rad, int kol) {
        this.rad = rad;
        this.kol = kol;
    }

    public void finn(Rute fra) {
        // Lar den vaere tom, overrider i hvit og aapning.
        // Kun sort vil bruke denne metoden som den er,
        // som er greit fordi den skal ikke lete videre.
        // Noe aapning ikke skal heller for saa vidt,
        // men vi kommer dit senere.
    }

    // Metoder for aa sette naboer i kardinalretningene
    public void settNord (Rute nord) {
        this.nord = nord;
    }
    public void settOest (Rute oest) {
        this.oest = oest;
    }
    public void settSyd (Rute syd) {
        this.syd = syd;
    }
    public void settVest (Rute vest) {
        this.vest = vest;
    }
}