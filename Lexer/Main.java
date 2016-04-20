import java.io.*;
import java.util.Scanner;

/**
 * Created by днс on 14.12.2014.
 */
public class Main {
    public static void main(String[] args) throws Exception
    {
        float numb = 123.45f;
        Long l = 123L;
        String start = "";
        Scanner scFileName = new Scanner(System.in);
        System.out.println("Which file would you like to divide into lexer?");
        String fileName = scFileName.next();
//        Scanner in = new Scanner(new File("C:/Users/днс/Desktop/untitled1/src/" + fileName));
        Scanner in = new Scanner(new File("C:/Users/днс/Desktop/ООП/lexer/src/" + fileName));
        while(in.hasNext())
            start += in.nextLine() + "\n";
        in.close();
        SimpleLexer lexer = new SimpleLexer(start);
        FileWriter out = new FileWriter("C:/Users/днс/Desktop/ООП/lexer/src/temp.txt");
        while (lexer.hasNextToken()){
            out.write(lexer.readNextToken().toString() + "\n");
        }
        out.close();
//
//        FileReader file = new FileReader("Main.java");
//        System.out.println(file);
//        System.out.println(f.read());
//        SimpleLexer res = new SimpleLexer()
    }
}
