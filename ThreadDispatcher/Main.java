import java.security.NoSuchAlgorithmException;

/**
 * Created by днс on 11.03.2015.
 */
public class Main {

    public static void main(String []args) throws NoSuchAlgorithmException {
        Monitor disp = new Monitor();
        Thread monitor = new Thread(disp);
        monitor.setDaemon(true);
        monitor.start();
        String path = System.getProperty("user.dir");
        FileFinder startFileFinder = new FileFinder(path + "/temp");
        startFileFinder.exec(disp);
        disp.isEnd = true;
        System.out.println(123456);
        monitor.stop();
        disp.executorService.shutdown();
    }
}
