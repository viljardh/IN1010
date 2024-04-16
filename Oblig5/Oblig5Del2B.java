import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Oblig5Del2B {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException, NullPointerException {
        int teller = 0;
//        try {
         for (int i = 1; i <= 1000; i++) {
             System.out.print(i + " ");
             if (i % 10 == 0) {
                 System.out.println("");
             }
             if (lok().hentForekomst() != 9 || !lok().hentSub().equals("QYF")) {
                 teller++;
             }
         }
//             } catch (NullPointerException e) {
//            teller++;
//        }
        System.out.println(lok());
         System.out.println("Ant feil: " + teller);
    }
    private static Subsekvens lok() throws FileNotFoundException, InterruptedException {

        // Orker ikke kommentere eller rydde denne, les Oblig5Hele

        String path = "TestDataLike/";
        ArrayList<String> fnvList = listFilnavn(path);
        ReentrantLock lock = new ReentrantLock();
        Condition kondis = lock.newCondition();

        int antTraa = 8;
        int teller = 0;
        Thread [] flettTraad = new Thread[antTraa];
        CountDownLatch ctl1 = new CountDownLatch(antTraa);

        int len = fnvList.size();
        SubsekvensRegister subReg = new SubsekvensRegister();
        CountDownLatch ctl2 = new CountDownLatch(len);
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

        for (int i = 0; i < antTraa; i++) {
            flettTraad[i] = new Thread(new FletteTrad(m));

            //m.setTeller(antTraa+1);
            flettTraad[i].start();
        }

        for (int i = 0; i < antTraa; i++) {
            flettTraad[i].join();
        }

        //while (m.storr() > 1) {
        //    ArrayList<HashMap<String, Subsekvens>> inn = m.hentUt();
        //    m.settInn(SubsekvensRegister.slaaSammen(inn.get(0), inn.get(1)));
        //}
        //ctl2.await();
        return m.hentStorst();
        //System.out.println(m.storr());
        //for (Thread t : flettTraad) {
        //    System.out.println(t.isAlive());
        //}
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
        scan.close();
        return fnvnList;
    }

}