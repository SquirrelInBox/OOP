/**
 * Created by днс on 14.12.2014.
 */
public class FloatReader extends Token {
    public FloatReader(String text)
    {
        super("fp", text);
    }

    public FloatReader()
    {
        super("fp", null);
    }

    public FloatReader(String text, Float numb)
    {
        super("fp", text, numb);
    }

    public FloatReader tryReadToken(String input)// нет 16ричных чисел
    {
        String floatNumber = searchToken("(\\+|\\-)?(\\d+)?.\\d+(f|F)", input);//десятичная форма (+/-)123.456(f/F)
        if (floatNumber != null)
            return new FloatReader(floatNumber, Float.parseFloat(floatNumber));
        floatNumber = searchToken("(\\+|\\-)?\\d+.(\\d+)?(f|F)", input);//десятичная форма (+/-)123.456(f/F), необязательная дробная часть
        if (floatNumber != null)
            return new FloatReader(floatNumber, Float.parseFloat(floatNumber));
        floatNumber = searchToken("(\\+|\\-)?(\\d+)?.\\d+(e|E)(\\+|\\-)\\d+(f|F)", input);//десятичная форма (+/-)1.2e+F
        if (floatNumber != null)
            return new FloatReader(floatNumber, Float.parseFloat(floatNumber));
        floatNumber = searchToken("(\\+|\\-)?\\d+.(\\d+)?(e|E)(\\+|\\-)\\d+(f|F)", input);
        if (floatNumber != null)
            return new FloatReader(floatNumber, Float.parseFloat(floatNumber));
        floatNumber = searchToken("\\d+(e|E\\d+)?(f|F)", input);//десятичная форма без дробной  части и точки
        if (floatNumber != null)
            return new FloatReader(floatNumber, Float.parseFloat(floatNumber));
        return null;
    }
}
