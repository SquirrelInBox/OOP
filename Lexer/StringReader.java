/**
 * Created by днс on 14.12.2014.
 */
public class StringReader extends Token{
    public StringReader(String text)
    {
        super("st", text);
    }

    public StringReader()
    {
        super("st", null);
    }

    public StringReader(String text, StringReader value)
    {
        super("st", text, value);
    }

    public StringReader tryReadToken(String input)
    {
        String str = "";
        if (input.charAt(0) == '"')
        {
            int index = 1;
            str += input.charAt(0);
            while (index < input.length() && input.charAt(index) != '"')
            {
                str += input.charAt(index);
                index++;
            }
            if (index < input.length() && input.charAt(index) != '"')
                return new StringReader(str, null);
            return new StringReader(str + '"');
        }
        return null;
    }
}
