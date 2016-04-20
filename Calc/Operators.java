
public class Operators extends Lexem {
    public final int priority;
    public Operators(String str)
    {
        super(str);
        priority = searchPriority(str.charAt(0));
    }

    public Operators()
    {
        super(null);
        priority = -1;
    }
    private static int searchPriority(char operator)
    {
        String operators = "+-*/^";
        int index = operators.indexOf(operator);
        return index / 2;
    }

    public Lexem parser(String str)
    {
        String lexema = searchLexem("(\\+|\\-|\\*|/|\\^)", str);
        if (lexema != null){ return new Operators(lexema);}
        return null;
    }
    public Lexem add(Lexem other){return null;}
    public Lexem multiply(Lexem other){return null;}
    public Lexem divide(Lexem other){return null;}
    public Lexem degree(Lexem other){return null;}
}
