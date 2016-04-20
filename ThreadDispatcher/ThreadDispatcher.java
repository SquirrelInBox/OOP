import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by днс on 19.03.2015.
 */
public class ThreadDispatcher{
    public final Map<Integer, Future<?>> listOfThreads;
    int countOfThreads;
    ExecutorService executorService;
    boolean isEnd;

    public ThreadDispatcher()
    {
//        создает карту, пул потоков
//        заводит счетчик потоков, флаг, по которому можно определить, когда закончить поток
        System.out.println(45613);
        this.listOfThreads = new TreeMap<Integer, Future<?>>();
        this.countOfThreads = 0;
        executorService = Executors.newFixedThreadPool(10);
//        Thread mainThread = new Thread(monitorThreads);
//        mainThread.run();
        isEnd = false;
    }

    int addThread(Runnable does) {
//        добавляет в список активных потоков,
//        присваивает номер и возвращает его
        synchronized (listOfThreads) {
            Future<?> thread  = executorService.submit(does);
            this.listOfThreads.put(this.countOfThreads, thread);
            System.out.println("add thread " + countOfThreads);
            countOfThreads += 1;
            return countOfThreads - 1;
        }
    }

    boolean isNotAlive(int ID){
        return this.listOfThreads.get(ID).isDone();
    }

    void onFinished(int ID){
        System.out.println("onFinished thread with ID " + ID);
    }

    protected void deleteThread(int ID) {
        if (!isNotAlive(ID)) {
            this.listOfThreads.remove(ID);
            onFinished(ID);
        }
    }

//    @Override
//    public void run() {
//        while (true){
//            synchronized (listOfThreads) {
//                Set<Integer> keys = listOfThreads.keySet();
//                for (int key : keys) {
//                    deleteThread(key);
//                }
//                if (this.isEnd) { break; }
//            }
//        }
//    }
}
