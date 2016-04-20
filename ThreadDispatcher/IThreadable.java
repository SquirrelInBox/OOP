import java.util.List;

/**
 * Created by днс on 19.03.2015.
 */
public interface IThreadable extends Runnable{
    void run();
    boolean init();
    void finishing();
}
