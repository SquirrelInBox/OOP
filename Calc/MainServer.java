
import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
import java.util.List;

/**
 * Created by днс on 10.04.2015.
 */
public class MainServer implements Runnable {
    ServerSocket ss;
    Socket d;
    InputStream in;
    OutputStream out;
    DataInputStream din;
    DataOutputStream dout;
    Socket threadSock;

    private static String calculate(String s){
        Parser p = new Parser(s);
        List<Lexem> lex = PostfixNotation.convertToPostfixNotation(p.parseExpression());
        Lexem resF = Calculated.calculate(lex);
        return resF.str;
    }

    public static void main(String[] args) {
        String variable = "-1+2";
        String answer = calculate(variable);
        System.out.println(answer);
    }

    public MainServer(Socket s){
        threadSock = s;
    }
    @Override
    public void run() {
        try {
            in = threadSock.getInputStream();
            out = threadSock.getOutputStream();
            din = new DataInputStream(in);
            dout = new DataOutputStream(out);
            byte buf[] = new byte[64*1024];
            int l = din.read(buf);
            String data = new String(buf, 0, l);
            System.out.println(data);
            String resF = calculate(data);
            System.out.println("___________");
            System.out.println(resF);
            out.write(resF.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                threadSock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
