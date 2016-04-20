import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by днс on 12.12.2014.
 */
public class IntReader extends Token{
    public IntReader(String type, String text, Number number)
    {
        super(type, text, number);
    }

    public IntReader()
    {
        super("i", null, null);
    }

    private boolean findIncorrectNumber(String pattern, String str)
    {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public Token tryReadToken(String input) {
        // TODO Задача 2: Доделать согласно спецификации целого числа в Java:
        // http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.1

        // На половину баллов — поддержка только десятичной системы исчисления.
        // На полный балл — полная поддержка всего, что есть в спецификации.
        // Написать модульные тесты в отдельном файле с использованием JUnit
        Pattern longInt = Pattern.compile("(l|L)");
        Matcher resLongInt;
        String integerNumber = this.searchToken("(-)?0x(a|b|c|d|e|f|A|B|C|D|E|F|\\d)+(l|L)?", input);//16ричное число
        if (integerNumber != null)
        {
            if ((longInt.matcher(integerNumber)).find())
                return new IntReader("i", integerNumber,
                        Long.parseLong(integerNumber.substring(2, integerNumber.length() - 1), 16));
            return new IntReader("i", integerNumber, Integer.parseInt(integerNumber.substring(2), 16));
        }
        integerNumber = this.searchToken("(-)?0\\d+(l|L)?", input);//8ричное число
        if (integerNumber != null)
        {
            if (this.findIncorrectNumber("(8|9)", integerNumber))
                return new IntReader("i", integerNumber, null);
            if ((longInt.matcher(integerNumber)).find())
                return new IntReader("i", integerNumber,
                        Long.parseLong(integerNumber.substring(1, integerNumber.length() - 1), 8));
            return new IntReader("i", integerNumber, Integer.parseInt(integerNumber.substring(1), 8));
        }
        integerNumber = this.searchToken("(-)?0(b|B)\\d+(l|L)?", input);//2ичное число
        if (integerNumber != null)
        {
            if (this.findIncorrectNumber("[2-9]", integerNumber))
                return new IntReader("i", integerNumber, null);
            if ((longInt.matcher(integerNumber)).find())
                return new IntReader("i", integerNumber,
                        Long.parseLong(integerNumber.substring(2, integerNumber.length() - 1), 2));
            return new IntReader("i", integerNumber, Integer.parseInt(integerNumber.substring(2), 2));
        }
        integerNumber = this.searchToken("(-)?\\d+(l|L)?", input);//десятичное число
        if (integerNumber != null)
        {
            if ((longInt.matcher(integerNumber)).find())
                return new IntReader("i", integerNumber,
                        Long.parseLong(integerNumber.substring(0, integerNumber.length() - 1)));
            return new IntReader("i", integerNumber, Integer.parseInt(integerNumber));
        }
        return null;
    }
}
