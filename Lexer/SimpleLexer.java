import java.util.ArrayList;
import java.util.List;

/**
 * Created by днс on 12.12.2014.
 */
public class SimpleLexer {
    public String input;
    public List<Token> output;
    private List<Token> arrTokens = new ArrayList<Token>();
    public int countToken;
    int index = 0;

    public SimpleLexer(String input)
    {
        this.input = input;
        this.arrTokens = setArrTokens();
        this.countToken = this.arrTokens.size();
        this.output = getTokens(input, this.countToken, this.arrTokens);
    }

    private static List<Token> setArrTokens()
    {
        List<Token> arr = new ArrayList<Token>();
        arr.add(new StringReader());
        arr.add(new CharReader());
        arr.add(new EndOfLineCommentReader());
        arr.add(new TraditionalCommentReader());
        arr.add(new FloatReader());
        arr.add(new IntReader());
        arr.add(new WordReader());
        arr.add(new IdentifierReader());
        arr.add(new OperandsReader());
        arr.add(new WhitespaceReader());
        arr.add(new BracketsReader());
        return arr;
    }

    private static List<Token> getTokens(String input, int countToken, List<Token> arrTokens)
    {
        List<Token> tokens = new ArrayList<Token>();
        int ind = 0;
        List<Token> tempList = new ArrayList<Token>();
        Token tempToken;
        while (ind < input.length())
        {
            for (int i = 0; i < countToken; i++)
            {
                tempToken = arrTokens.get(i).tryReadToken(input.substring(ind));
                if (tempToken != null)
                {
                    tempList.add(tempToken);
                }
            }
            if (tempList.size() > 0)
            {
                if (tempList.get(0).getValue() != null)
                    tokens.add(tempList.get(0));
                ind += tempList.get(0).getText().length();
            } else {
                ind ++;
            }
            tempList.clear();
        }
        return tokens;
    }

    public boolean hasNextToken()
    {
        return  this.index < this.output.size();
    }

    public Token readNextToken()
    {
        if (hasNextToken()){
            index++;
            return this.output.get(this.index - 1);
        }
        return null;
    }
}
