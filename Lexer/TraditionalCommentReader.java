/**
 * Created by днс on 14.12.2014.
 */
public class TraditionalCommentReader extends Token {
    public TraditionalCommentReader(String text)
    {
        super("tk", text);
    }

    public TraditionalCommentReader()
    {
        super("tk", null);
    }

    public TraditionalCommentReader(String text, String value)
    {
        super("tk", text, value);
    }

    public TraditionalCommentReader tryReadToken(String input)
    {
        String startComment = searchToken("/\\*", input);
        if (startComment != null)
        {
            int index = 2;
            String endComment = null;
            while ((index < input.length())&&(endComment == null))
            {
                endComment = searchToken("\\*/", input.substring(index));
                index ++;
            }
            if (index == input.length())
            {
                return new TraditionalCommentReader(input, null);
            }
            return new TraditionalCommentReader(input.substring(0, index + 1));
        }
        return null;
    }
}
