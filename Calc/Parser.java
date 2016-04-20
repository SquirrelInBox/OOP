import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    public String expression;
    private final List<Lexem> arrLexems;
    private final int countLexems;

    public Parser(String str)
    {
        expression = str;
        arrLexems = setArrLexem();
        countLexems = arrLexems.size();
    }

    private static List<Lexem> setArrLexem()
    {
        List<Lexem> arr = new ArrayList<Lexem>();
        arr.add(new VectorND());
        arr.add(new Brackets());
        arr.add(new Operators());
        arr.add(new Numbers());
        return arr;
    }

    public List<Lexem> parseExpression()
    {
        List<Lexem> lexems = new ArrayList<Lexem>();
        int ind = 0;
        List<Lexem> tempList = new ArrayList<Lexem>();
        Lexem tempLexem;
        while (ind < expression.length())
        {
            for (int i = 0; i < countLexems; i++)
            {
                tempLexem = arrLexems.get(i).parser(expression.substring(ind));
                if (tempLexem != null)
                {
                    tempList.add(tempLexem);
                }
            }
            if (tempList.size() > 1)
            {
                Lexem changeLexem;
                if (tempList.get(0).str.substring(0, 2).equals("((")) {
                    changeLexem = tempList.get(1);
                } else{
                    changeLexem = tempList.get(0);
                }
                lexems.add(changeLexem);
                ind += changeLexem.str.length();
            }else if (tempList.size() > 0) {
                lexems.add(tempList.get(0));
                ind += tempList.get(0).str.length();
            }else{
                ind ++;
            }
            tempList.clear();
        }
        return lexems;
    }
}
