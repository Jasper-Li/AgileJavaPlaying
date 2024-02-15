import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharacterTest {
    @Test
    void isJavaIdentifier() {
        assertTrue(Character.isJavaIdentifierPart('a'));
        assertFalse(Character.isJavaIdentifierPart('^'));
    }
    @Test
    void isWhiteSpace() {
        final var spaces = new char[]{' ', '\t', '\n'};
        for (final char c : spaces) {
            assertTrue(Character.isWhitespace(c));
        }
        assertFalse(Character.isWhitespace('a'));

    }

}
