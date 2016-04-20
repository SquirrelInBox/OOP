import java.util.regex.Pattern;

public class Brackets extends Lexem {
    final public int priority;

    public Brackets(String str)
    {
        super(str);
        priority = searchPriority(str);
    }

    public Brackets()
    {
        super(null);
        priority = -1;
    }

    private static int searchPriority(String bracket)
    {
        if ((bracket.equals("("))||(bracket.equals("["))||(bracket.equals("<")))
            return 0;
        return 1;
    }
    public Brackets parser(String str)
    {
        String lexema = searchLexem("(\\(|\\)|\\[|\\]|<|>)", str);
        if (lexema != null){ return new Brackets(lexema);}
        return null;
    }
    public Lexem add(Lexem other){return null;}
    public Lexem multiply(Lexem other){return null;}
    public Lexem divide(Lexem other){return null;}
    public Lexem degree(Lexem other){return null;}
}
