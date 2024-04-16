import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Oblig5Del1 {
    public static void main(String[] args) throws FileNotFoundException {

        // Orker ikke kommentere eller rydde denne, les Oblig5Hele

        SubsekvensRegister subReg = new SubsekvensRegister();
        String path = args[0];

        ArrayList<String> fnvList = listFilnavn(path);

        for (String s : fnvList) {
            subReg.settInn(SubsekvensRegister.lesInn(s));
        }

        HashMap<String, Subsekvens> subMap1, subMap2, subMap3;
        while (subReg.storr() > 1) {
            subMap1 = subReg.taUt();
            subMap2 = subReg.taUt();
            subMap3 = SubsekvensRegister.slaaSammen(subMap1, subMap2);
            subReg.settInn(subMap3);
        }

        System.out.println("Stoerste (Subsekvens,forekomst) er " + subReg.hentStorst());
    }

    private static ArrayList<String> listFilnavn (String path) throws FileNotFoundException {
        ArrayList<String> fnvnList = new ArrayList<>();
        String fnvn = "metadata.csv";
        File input = new File(path + fnvn);
        Scanner scan = new Scanner(input);
        while (scan.hasNextLine()) {
            String[] line = scan.nextLine().split(",");
            fnvnList.add(path + line[0]);
        }
        return fnvnList;
    }
}