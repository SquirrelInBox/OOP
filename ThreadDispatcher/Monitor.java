import java.util.Set;

/**
* Created by днс on 27.03.2015.
*/
public class Monitor extends ThreadDispatcher implements Runnable {

    public Monitor(){}

    @Override
    public void run() {
        while (true){
            synchronized (listOfThreads) {
                Set<Integer> keys = listOfThreads.keySet();
                for (int key : keys) {
                    deleteThread(key);
                }
                if (this.isEnd) { break; }
            }
        }
    }
}
