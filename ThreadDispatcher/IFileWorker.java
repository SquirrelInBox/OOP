import java.io.File;
import java.security.NoSuchAlgorithmException;

/**
 * Created by днс on 11.03.2015.
 */
public interface IFileWorker {
    public void work();
    public void md5() throws NoSuchAlgorithmException;
}
