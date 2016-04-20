/**
 * Created by днс on 15.12.2014.
 */
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EndOfLineCommentReaderTest {
    private EndOfLineCommentReader reader = new EndOfLineCommentReader();

    @Test
    public void testNotComment()
    {
        assertNull(reader.tryReadToken("123456//45"));
        assertNull(reader.tryReadToken("/12345"));
    }

    @Test
    public void testComment()
    {
        checkComment("//12345687", "//12345687");
        checkComment("//qwe 12", "//qwe 12");
        checkComment("//", "//");
        checkComment("// //789", "// //789");
    }

    private void checkComment(String input, String expectedToken) {
        EndOfLineCommentReader token = reader.tryReadToken(input);
        assertEquals("EndOfLineCommentReader", token.getType());
        assertEquals(expectedToken, token.getText());
    }
}
