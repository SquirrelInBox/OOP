import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by днс on 12.12.2014.
 */
public class EndOfLineCommentReader extends Token{
    public EndOfLineCommentReader(String type, String str)
    {
        super(type, str);
    }

    public EndOfLineCommentReader()
    {
        super("EndOfLineCommentReader", null, null);
    }

    public EndOfLineCommentReader tryReadToken(String input)
    {
        String comment = this.searchToken("(//)", input);
        if (comment != null) {
            int i = 2;
            String find = "";
            Pattern p = Pattern.compile("\\n");
            while (i < input.length() && "".equals(find))
            {
                Matcher m = p.matcher(input.substring(0, i + 1));
                if (m.find())
                    find = m.group();
                comment += input.charAt(i);
                i++;
            }
//            System.out.println(comment.substring(0, comment.length()));
            return new EndOfLineCommentReader(this.getClass().getName(), comment);
        }
        return null;
    }
}
