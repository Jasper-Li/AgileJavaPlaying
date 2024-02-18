import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lesson7 {
    private int factorialWithWhile(int n){
        var result = 1;
        int i = 2;
        while(i<=n){
            result *= i;
            ++i;
        }
        return result;
    }
    private int factorialWithFor(int n){
        var result = 1;
        for(int i=2; i<=n; ++i){
            result *= i;
        }
        return result;
    }
    private int factorialWithDoWhile(int n) {
        var result = 1;
        int i = 1;
        do{
            result *= i;
            ++i;
        }while(i <= n);
        return result;
    }
    private int factorialWithWhileTrue(int n){
        var result = 1;
        int i = 2;
        while(true){
            if(i > n) break;
            result *= i;
            ++i;
        }
        return result;
    }

    /**
     * Lesson 7, exercise 1.
     */
    @Test
    void testFactorial() {
        record Check(int value, int result){}
        Check[] checks = {
            new Check(0, 1),
            new Check(1, 1),
            new Check(2, 2),
            new Check(3, 6),
            new Check(4, 24),
            new Check(5, 120),
            new Check(6, 720),
            new Check(7, 5040),
            new Check(8, 40320),
        };
        for (final var check: checks){
            final var msg = STR."Check \{check.value}";
            assertEquals(check.result, factorialWithWhile(check.value), msg);
            assertEquals(check.result, factorialWithFor(check.value), msg);
            assertEquals(check.result, factorialWithDoWhile(check.value), msg);
            assertEquals(check.result, factorialWithWhileTrue(check.value), msg);
        }
    }

    // Without continue keyword, code is clearer.
    private String getString(int n){
        if(n < 1) return "";
        final var builder = new StringBuilder();
        builder.append(1);
        for(int i=2; i<=n; ++i ){
            builder.append(' ');
            builder.append(i);
            if(i%5 == 0){
                builder.append('*');
            }
        }
        return builder.toString();
    }

    /**
     * Lesson 7, exercise 2.
     */
    private record Check7_2(int n, String result){}
    private final Check7_2[] checks_7_2 = {
        new Check7_2(0, ""),
        new Check7_2(1, "1"),
        new Check7_2(2, "1 2"),
        new Check7_2(3, "1 2 3"),
        new Check7_2(5, "1 2 3 4 5*"),
        new Check7_2(8, "1 2 3 4 5* 6 7 8"),
        new Check7_2(10, "1 2 3 4 5* 6 7 8 9 10*"),
        new Check7_2(12, "1 2 3 4 5* 6 7 8 9 10* 11 12"),
    };
    @Test
    void testString(){
        for(final var check: checks_7_2){
            final var n = check.n;
            assertEquals(check.result, getString(n), STR."Check \{n}");
        }
    }

    private Vector<String> getSplitStrins(int n){
        if(n<1) return new Vector<>();
        final var string = getString(n);
        return new Vector<String>(Arrays.asList(string.split(" ")));
    }
    /**
     * Lesson 7, exercise 3.
     */
    @Test
    void splitString(){
        record Check(int n, Vector<String> strings){}
        final Check[] checks = {
            new Check(0, new Vector<>()),
            new Check(1, new Vector<>(List.of("1"))),
            new Check(2, new Vector<>(List.of("1", "2"))),
            new Check(3, new Vector<>(List.of("1", "2", "3"))),
            new Check(5, new Vector<>(List.of("1", "2", "3", "4", "5*"))),
            new Check(8, new Vector<>(List.of("1", "2", "3", "4", "5*", "6", "7", "8"))),
            new Check(10, new Vector<>(List.of("1", "2", "3", "4", "5*", "6", "7", "8", "9", "10*"))),
            new Check(12, new Vector<>(List.of("1", "2", "3", "4", "5*", "6", "7", "8", "9", "10*", "11", "12"))),
        };
        for(int i = 0; i<checks.length; ++i) {
            final var check = checks[i];
            final var n = check.n;
            final var result7_3_1 = getSplitStrins(n);
            final var msgCheck = STR."Check \{n}";
            assertEquals(check.strings, result7_3_1, msgCheck);
            assertEquals(checks_7_2[i].result, recreateString(result7_3_1), msgCheck);

        }
    }
    private String recreateString(Vector<String> strings) {
        final var builder = new StringBuilder();
        final var elements = strings.elements();
        boolean firstElements = true;
        while(elements.hasMoreElements()){
            if(firstElements){
                firstElements = false;
            } else {
                builder.append(' ');
            }
            builder.append(elements.nextElement());
        }
        return builder.toString();
    }

}
