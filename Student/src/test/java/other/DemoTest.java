package other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoTest {
    private static boolean isPalindrome(String string) {
        if(string.isEmpty()) return true;
        for (int forward = 0, backward = string.length() - 1;
             forward <= string.length()/2;
             forward++, backward--)
            if (string.charAt(forward) != string.charAt(backward))
                return false;
        return true;
    }
    // tests
    @Test
    public void testPalindrome() {
        assertFalse(isPalindrome("abcdef"));
        assertFalse(isPalindrome("abccda"));
        assertTrue(isPalindrome("abccba"));
        assertFalse(isPalindrome("abcxba"));
        assertTrue(isPalindrome("a"));
        assertTrue(isPalindrome("aa"));
        assertFalse(isPalindrome("ab"));
        assertTrue(isPalindrome(""));
        assertTrue(isPalindrome("aaa"));
        assertTrue(isPalindrome("aba"));
        assertTrue(isPalindrome("abbba"));
        assertTrue(isPalindrome("abba"));
        assertFalse(isPalindrome("abbaa"));
        assertFalse(isPalindrome("abcda"));
    }

}
