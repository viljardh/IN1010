import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Labyrint labyrint = new Labyrint();
        String inn = "7.in";
        labyrint.lesInn("labyrinter/" + inn);
        System.out.println(labyrint);
        int rad = Integer.parseInt(args[0]);
        int kol = Integer.parseInt(args[1]);
        labyrint.finnUtveiFra(rad, kol);
    }
}
