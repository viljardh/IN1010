import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor1 extends SubsekvensRegister {

    private final ReentrantLock lock = new ReentrantLock();

    SubsekvensRegister subReg;

    Monitor1 (SubsekvensRegister subReg) {
        this.subReg = subReg;
    }

    @Override
    public void settInn(HashMap<String, Subsekvens> reg) {
        lock.lock();
            try {
                subReg.settInn(reg);
            } finally {
                lock.unlock();
            }
    }



    @Override
    public HashMap<String, Subsekvens> taUt() {
        lock.lock();
        try {
           return subReg.taUt();
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


    public HashMap<String, Subsekvens> les(String fnvn) throws FileNotFoundException {
        lock.lock();
        try {
            return SubsekvensRegister.lesInn(fnvn);
        } finally {
            lock.unlock();
        }
    }

    public HashMap<String, Subsekvens> flett (HashMap<String, Subsekvens> h1, HashMap<String, Subsekvens> h2) {
        lock.lock();
        try {
            return slaaSammen(h1, h2);
        } finally {
            lock.unlock();
        }
    }



    @Override
    public Subsekvens hentStorst() {
        lock.lock();
        try {
            return subReg.hentStorst();
        } finally {
            lock.unlock();
        }
    }
}
