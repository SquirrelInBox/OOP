/**
 * Created by днс on 12.12.2014.
 */
public class IdentifierReader extends Token{
    public IdentifierReader(String type, String text)
    {
        super(type, text);
    }

    public IdentifierReader()
    {
        super("IdentifierReader", null, null);
    }
    public Token tryReadToken(String input) {
        // TODO Задача 1: доделать по аналогии с WhitespaceReader
        // Должен вести себя в полном соответствии со спецификацией
        // IdentifierChars:
        // * JavaLetter
        // * IdentifierChars JavaLetterOrDigit
        //
        // http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.8
        String word = searchToken("[a-zA-Z]+(_+|[a-zA-Z]+|\\d+)*", input);
        if (word != null)
            return new IdentifierReader(this.getClass().getName(), word);
        return null;
    }
}
