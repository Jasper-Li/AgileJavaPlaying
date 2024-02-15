package util;

import org.junit.jupiter.api.Test;
import java.lang.System;

import static java.lang.StringTemplate.STR;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @Test
    void appendNewLine() {
        if (util.System.isWindows()) {
            final var newLineWin = "\r\n";
            assertEquals(newLineWin, StringUtil.NEW_LINE);

            final var string = "hello world";
            assertEquals(string + newLineWin, StringUtil.appendNewLine(string));
        }
    }
    @Test
    void stringTemplate() {
        var city = "ShangHai";
        var country = "China";
        var message = STR."\{city} is in \{country}";
        java.lang.System.out.println(message);

        System.out.println("ABC".repeat(3));

        var s1 = "String1";
        var s2 = "String2";
        char[] words = {
            0x0043, 0x0068, 0x0065, 0x0065, 0x0072, 0x0073, 0x0021
        };
        for (var c : words) {
            System.out.print(c);
        }
        char han = '汉';
        System.out.print(han);
        var sentence = "测试中的";
        System.out.print(sentence.length());
    }

    @Test
    void isEqualIgnoreEOL() {
        record Check(String left, String right, boolean expected) {
        }
        ;
        Check[] checks = {
            new Check(
                """
                .KR.....\r
                P.PB....\r
                .P..Q...\r
                ........\r
                .....nq.\r
                .....p.p\r
                .....pp.\r
                ....rk..\r
                """, """
                .KR.....
                P.PB....
                .P..Q...
                ........
                .....nq.
                .....p.p
                .....pp.
                ....rk..
                """,
                true
            ),
            new Check(
                """
                .KR.....
                P.PB....
                .P..Q...
                ........
                .....nq.
                .....p.p
                .....pp.
                ....rk..
                """, """
                .KR.....
                P.PB....
                .P..Q...
                ........
                .....nq.
                .....p.p
                .....pp.
                ....rk..
                """,
                true
            ),
            new Check(
                """
                .KR....P
                P.PB....
                .P..Q...
                ........
                .....nq.
                .....p.p
                .....pp.
                ....rk..
                """, """
                .KR.....
                P.PB....
                .P..Q...
                ........
                .....nq.
                .....p.p
                .....pp.
                ....rk..
                """,
                false
            ),
        };
        for (var check : checks) {
            assertEquals(check.expected, StringUtil.isEqualIgnoreEOL(check.left, check.right));
        }
    }
}
