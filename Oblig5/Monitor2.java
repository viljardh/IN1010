import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Monitor2 extends SubsekvensRegister {

    // Deklarere litt ting vi trenger (og jeg ikke er helt sikker paa
    // om vi trenger, vi kommer dit
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition kondis = lock.newCondition();
    private final SubsekvensRegister subReg;

    // Monitoren skal referere til et Subsystemregister vi erklaerer i main()
    Monitor2 (SubsekvensRegister subReg) {
        this.subReg = subReg;
    }

    // Jeg vet ikke helt hva eller hvorfor, men jeg dupliserte alle metoder
    // bortsett fra de statiske og overridet de for aa legge de under
    // laas og slaa.
    @Override
    public void settInn(HashMap<String, Subsekvens> reg) {
        lock.lock();
        try {
            subReg.settInn(reg);
            //kondis.signalAll();
        } finally {
            lock.unlock();
        }
    }

    // Modifisert metode som gir ut en arraylist med to hashmaps
    // sann at vi slipper en pinlig dealock hvor to traader har
    // en hashmap hver, og ikke faar flettet
    public ArrayList<HashMap<String, Subsekvens>> hentUt() {
        lock.lock();
        ArrayList<HashMap<String, Subsekvens>> out = new ArrayList<>();
        try {
            out.add(subReg.taUt());
            out.add(subReg.taUt());

            return out;

            } finally {
            lock.unlock();
        }
    }


    // Vil ikke at man skal sette inn og ta ut samtidig
    @Override
    public HashMap<String, Subsekvens> taUt() {
        lock.lock();
        try {
            return subReg.taUt();
        } finally {
            lock.unlock();
        }
    }

    // Ikke hundre prosent paa hvorfor de neste to var noedvendige
    // men satte det saann og naa er alt saa meget bedre.
    // Fikk rare feil hvis ikke.
    @Override
    public Subsekvens hentStorst() {
        lock.lock();
        try {
            return subReg.hentStorst();
        } finally {
            lock.unlock();
        }
    }
    @Override
    public int storr() {
        lock.lock();
        try {
            return subReg.storr();
        } finally {
            lock.unlock();
        }
    }




}