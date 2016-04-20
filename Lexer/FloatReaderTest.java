/**
 * Created by днс on 15.12.2014.
 */
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class FloatReaderTest {
    private FloatReader reader = new FloatReader();

    @Test
    public void testNotANumber()
    {
        assertNull(reader.tryReadToken("12345e"));
    }

    @Test
    public void testNumber()
    {
        checkComment("1e1f", "1e1f", 10.0f);
        checkComment("2.f", "2.f", 2.0f);
        checkComment("0.3f", "0.3f", 0.3f);
        checkComment(".3f", ".3f", 0.3f);
        checkComment("6.022137e+23f", "6.022137e+23f", 6.022137E23f);

    }

    private void checkComment(String input, String expectedToken, Float expectValue) {
        FloatReader token = reader.tryReadToken(input);
        assertEquals("fp", token.getType());
        assertEquals(expectedToken, token.getText());
        assertEquals(expectValue, token.getValue());
    }
}
