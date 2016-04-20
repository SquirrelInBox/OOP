/**
 * Created by днс on 12.12.2014.
 */
public class WordReader extends Token{
//    private final String word;
    private final String[] listWord = {"abstract", "continue", "for", "new", "switch",
            "assert", "default", "if", "package", "synchronized", "boolean", "do", "goto",
            "private", "this", "break", "double", "implements", "protected", "throw",
            "byte", "else", "import", "public", "throws", "case", "enum", "instanceof",
            "return", "transient", "catch", "extends", "int", "short", "try", "char",
            "final", "interface", "static", "void", "class", "finally", "long", "strictfp",
            "volatile", "const", "float", "native", "super", "while"};

    // TODO Задача 1: Добавить параметр ignoreCase — чувствительность к регистру
    public WordReader(String word) {
        super("kw", word);
//        this.word = word;
    }

    public WordReader()
    {
        super("kw", null, null);
//        this.word = null;
    }

    public Token tryReadToken(String input) {
//        if (input.startsWith(word)) {
//            return new WordReader(word);
//        }
//        return null;
//        if ((input.length() >= word.length())&&(input.substring(0, word.length()).compareToIgnoreCase(word) == 0))
//            return new WordReader(word);
//        return null;
        for (int i = 0; i < listWord.length; i++)
        {
            if (input.startsWith(listWord[i]))
            {
                return new WordReader(listWord[i]);
            }
        }
        return null;
    }

//    public static void main(String[] args) {
//        WordReader reader = new WordReader("hello");
//        System.out.println("Testing WordReader...");
//        test(reader, "", null);
//        test(reader, "H", null);
////        test(reader, "Hello world", null);
//        test(reader, "hell", null);
//        test(reader, "hellO", "hello");
//        test(reader, "hello world", "hello");
//        System.out.println("Testing finished");
//    }
//
//    public static void test(WordReader r, String input, String expected) {
//        Token actualToken = r.tryReadToken(input);
//        if (expected == null) {
//            if (actualToken != null) {
//                System.out.println("ERROR: on " + input + " read "
//                        + actualToken);
//            }
//        } else {
//            if (actualToken == null) {
//                System.out.println("ERROR: on " + input + " read "
//                        + actualToken);
//                return;
//            }
//            if (!expected.equals(actualToken.getText())) {
//                System.out.println("ERROR: on " + input + " expected "
//                        + expected + " but was " + actualToken);
//            }
//        }
//    }
}
