/**
 * Created by днс on 15.12.2014.
 */
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class OperandsReaderTest {
    private OperandsReader reader = new OperandsReader();
     @Test
     public void testOperands()
     {
         checkComment("+78", "+");
         checkComment("/78", "/");
         checkComment(">>> 75", ">>>");
         checkComment("<<75", "<<");
         checkComment(">>75", ">>");
         checkComment("<75", "<75");
         checkComment(">75", ">75");
     }

    private void checkComment(String input, String expectedToken) {
        OperandsReader token = reader.tryReadToken(input);
        assertEquals("op", token.getType());
        assertEquals(expectedToken, token.getText());
    }
}
