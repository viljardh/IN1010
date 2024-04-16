public class Bil3 {

    private String regnr;

    public Bil3(String reg){
        regnr = reg;
    }

    public String hentNummer(){
        return regnr;
    }

    public void skriv(){
        System.out.println("Jeg er bil med regnr " + regnr);
    }
}

