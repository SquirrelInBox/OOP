import java.io.File;
import java.security.NoSuchAlgorithmException;

/**
 * Created by днс on 11.03.2015.
 */
public class FileFinder {
    public File m_path;

    public FileFinder(String path){
        m_path = new File(path);
    }

    private void ex(File[] fList, ThreadDispatcher dispatcher) throws NoSuchAlgorithmException {
//        dispatcher.addThread((Runnable) new FileWorker(m_path));
        for (File aFList : fList) {
            if (aFList.isFile()) {
                dispatcher.addThread((Runnable) new FileWorker(aFList));
            } else {
                m_path = aFList.getAbsoluteFile();
                this.exec(dispatcher);
            }
        }
    }
    
    public void exec(ThreadDispatcher dispatcher) throws NoSuchAlgorithmException {
        File[] fList = m_path.listFiles();
        System.out.println(m_path);
        if (fList != null) {
            ex(fList, dispatcher);
        }
        else if(!(m_path.isFile()))
        {
            System.err.println("It's not path");
        }
    }
}
