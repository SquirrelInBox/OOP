/**
 * Created by днс on 14.12.2014.
 */
public class BracketsReader extends Token{
    public BracketsReader(String text)
    {
        super("br", text);
    }

    public BracketsReader()
    {
        super("br", null);
    }

    public BracketsReader tryReadToken(String input)
    {
        String bracket = this.searchToken("(\\(|\\)|\\[|\\]|\\{|\\}|<|>)", input);
        if (bracket != null)
            return new BracketsReader(bracket);
        return null;
    }
}
