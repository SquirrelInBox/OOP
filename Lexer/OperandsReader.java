/**
 * Created by днс on 14.12.2014.
 */
public class OperandsReader extends Token{
    public OperandsReader(String text)
    {
        super("op", text);
    }

    public OperandsReader()
    {
        super("op", null);
    }

    public OperandsReader tryReadToken(String input)
    {
        String operand = this.searchToken("(\\.|\\*|\\/|\\-|\\+|=|>>>|<<|>>|\\^|%|!|&|\\||~|>|<)", input);
        if (operand != null)
            return new OperandsReader(operand);
        return null;
    }
}
