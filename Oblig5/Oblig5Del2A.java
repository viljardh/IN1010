import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Oblig5Del2A {
    public static void main(String[] args) throws FileNotFoundException {

        // Orker ikke kommentere eller rydde denne, les Oblig5Hele

        String path = args[0];
        ArrayList<String> fnvList = listFilnavn(path);
        int Teller = 0;

        int len = fnvList.size();
        SubsekvensRegister subReg = new SubsekvensRegister();
        CountDownLatch ctl = new CountDownLatch(8);

        Monitor2 m = new Monitor2(subReg);
        Thread[] lesTraad = new Thread[len];
        for (int i = 0; i < len; i++) {
            lesTraad[i] =  new Thread(new LeseTrad(fnvList.get(i), m));
            lesTraad[i].start();
        }

        for (int i = 0; i < len; i++) {
            try {
                lesTraad[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        HashMap<String, Subsekvens> subMap1, subMap2, subMap3;
        while (m.storr() > 1) {
            subMap1 = m.taUt();
            subMap2 = m.taUt();
            subMap3 = SubsekvensRegister.slaaSammen(subMap1, subMap2);
            m.settInn(subMap3);
        }

        System.out.println("Stoerste (Subsekvens,forekomst) er " + m.hentStorst());
        /*
        for (Thread t : lesTraad) {
            System.out.println(t.isAlive());
        }

         */

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