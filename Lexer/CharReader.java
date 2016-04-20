/**
 * Created by днс on 16.12.2014.
 */
public class CharReader extends Token {
    public CharReader(String text)
    {
        super("ch", text);
    }

    public CharReader()
    {
        super("ch", null);
    }

    public CharReader(String text, StringReader value)
    {
        super("ch", text, value);
    }

    public CharReader tryReadToken(String input)
    {
        String str = "";
        String temp = Character.toString(input.charAt(0));
        if ("'".equals(temp))
        {
            int index = 1;
            str += temp;
            temp = Character.toString(input.charAt(index));
            while (index < input.length() && (!("'".equals(temp))))
            {
                str += input.charAt(index);
                temp = Character.toString(input.charAt(index));
                index++;
            }
            if (index < input.length()
                    && !"'".equals(Character.toString(input.charAt(index - 1)))
                    || str.length() > 3)
                return new CharReader(str, null);
            return new CharReader(str);
        }
        return null;
    }
}
