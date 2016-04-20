/**
 * Created by днс on 12.12.2014.
 */
public class WhitespaceReader extends Token{
    /**
     * Читает с начала строки максимальное количество пробельных символов.
     *
     * @return Возвращает прочитанный префикс строки.
     */
    public WhitespaceReader(String type, String text)
    {
        super(type, text);
    }

    public WhitespaceReader()
    {
        super("ws", null);
    }

    public Token tryReadToken(String input) {
        int i = 0;
        int len = input.length();
        while (i < len && Character.isWhitespace(input.charAt(i)))
            i++;
        if (i > 0)
            return new WhitespaceReader("ws", input.substring(0, i));
        else
            return null;
    }
}
