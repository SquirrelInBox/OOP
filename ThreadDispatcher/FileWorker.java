import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.security.MessageDigest.getInstance;

/**
 * Created by днс on 11.03.2015.
 */
public class FileWorker implements IFileWorker, IThreadable {
    File tempFile;

    public FileWorker(File file){
        this.tempFile = file;
    }

    @Override
    public void work() {
        if (tempFile.isFile())
            System.out.println(tempFile.getPath() + ' ' + tempFile.getName() + ' ' + tempFile.length());
    }

    private void filesToZip(){
        try {
            File f = new File(tempFile.getName() + "1.zip");
            if (!f.exists()){f.createNewFile();}
            System.out.println(tempFile);
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(f));
            try {
                zout.putNextEntry(new ZipEntry(tempFile.getName()));
            } catch (IOException e) {
                e.printStackTrace();

            }
            InputStream in = new FileInputStream(tempFile);
            long sizeFile = tempFile.length();
            Double tempSize = 0.0;
            try {
                byte[] buffer = new byte[1024*1024];
                while (true) {
                    int readCount = in.read(buffer);
                    if (readCount < 0) {
                        break;
                    }
                    System.out.println(tempSize);
                    System.out.println(tempFile.getName() + " archive " + ((tempSize * 100 / sizeFile) + "%"));
                    tempSize += 1024;
                    zout.write(buffer, 0, readCount);
                }
                System.out.println(tempFile.getName() + " 100%");
            } finally {
                in.close();
            }
            zout.closeEntry();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void md5() throws NoSuchAlgorithmException {
        String nameFile = tempFile.getPath();
        if (tempFile.isFile())
            nameFile = ' ' + tempFile.getName();
        MessageDigest md = getInstance("MD5");
        md.reset();
        md.update(nameFile.getBytes());
        byte[] d = md.digest();
        String result = (new BigInteger(1, d)).toString(16);
        System.out.println(nameFile + ' ' + result);
    }

    @Override
    public void run() {
        init();
        filesToZip();
        finishing();
    }

    @Override
    public boolean init() {
        System.out.println("Thread with name " + tempFile.getPath() + " start work \n");
        return false;
    }

    @Override
    public void finishing() {
        System.out.println("Thread with name " + tempFile.getPath() + " finish work \n");
    }
}
