/**
 * Created by днс on 12.12.2014.
 */
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
//TODO junit.framework — это сторонняя библиотека, которую нужно добавить к вашему проекту.
//Eclipse может вам в этом помочь — поместите курсор на подчеркнутое слово, нажмите Ctrl+1 и выберите пункт Fix project setup

public class IntReaderTest {
    // Запуск тестов:
    // сочетанием клавиш Alt+Shift+X, T
    // или пунктом контекстного меню (по правой кнопке) -> Run As -> JUnit Test
    private IntReader reader = new IntReader();

    @Test
    public void testNotANumbers() {
        assertNull(reader.tryReadToken(""));
        assertNull(reader.tryReadToken("qwerty"));
        assertNull(reader.tryReadToken("s12"));
        // TODO: Добавить ещё тестов!

    }

    @Test
    public void testDecimal() {
        checkInt("0", "0", 0);
        checkInt("1", "1", 1);
        checkInt("12346L", "12346L", Long.valueOf(12346));
        checkInt("99999999999L", "99999999999L", 99999999999L);
        // TODO: Добавить ещё тестов!
    }

    @Test
    public void testHex() {
        checkInt("0xA", "0xA", 10);
        checkInt("0xa7", "0xa7", 167);
        checkInt("0x0f54C", "0x0f54C", 62796);
        checkInt("0x00000AbCd", "0x00000AbCd", 43981);
        checkInt("0x123AL", "0x123AL", 4666L);
        checkInt("0x1A4f5dl", "0x1A4f5dl", 1724253L);
        // TODO: Добавить ещё тестов!
    }

    @Test
    public void testBinary() {
        checkInt("0b0", "0b0", 0);
        checkInt("0B0111001", "0B0111001", 57);
        checkInt("0b1112", "0b1112", null);
        checkInt("0b0111111L", "0b0111111L", 63l);
        checkInt("0b11111111111111111111111111111111111111111111111111111111111111l",
                "0b11111111111111111111111111111111111111111111111111111111111111l",
                4611686018427387903l);
        // TODO: Добавить ещё тестов!
    }

    @Test
    public void testOct() {
        checkInt("0123456", "0123456", 42798);
        checkInt("01234689", "01234689", null);
        checkInt("01234L", "01234L", 668L);
        checkInt("01234", "01234", 668);
        // TODO: Добавить ещё тестов!
    }

    private void checkInt(String input, String expectedToken, Number expectedValue) {
        Token token = reader.tryReadToken(input);
        assertEquals("i", token.getType());
        assertEquals(expectedToken, token.getText());
        assertEquals(expectedValue, token.getValue());

    }
}
