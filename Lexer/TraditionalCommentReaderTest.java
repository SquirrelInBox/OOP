/**
 * Created by днс on 15.12.2014.
 */
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TraditionalCommentReaderTest {
    private TraditionalCommentReader reader = new TraditionalCommentReader();

    @Test
    public void testNotComment()
    {
        assertNull(reader.tryReadToken("qwer"));
        assertNull(reader.tryReadToken("//qwert"));
        assertNull(reader.tryReadToken("/qwer*"));
    }

    @Test
    public void testComment()
    {
        checkComment("/*qwer*/", "/*qwer*/", "/*qwer*/");
        checkComment("/*qwer", "/*qwer", null);
        checkComment("/*qwee/", "/*qwee/", null);

    }
    private void checkComment(String input, String expectedToken, String value) {
        Token token = reader.tryReadToken(input);
        assertEquals("tk", token.getType());
        assertEquals(expectedToken, token.getText());
    }
}
