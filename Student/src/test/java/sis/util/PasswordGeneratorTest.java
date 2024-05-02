package sis.util;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordGeneratorTest {
    class MockRandom extends Random {
        int i ;
        MockRandom(char startCharValue) {
            i = startCharValue - PasswordGenerator.LOW_END_PASSWORD_CHAR;
        }

        @Override
        protected int next(int bits) {
            return i++;
        }
    }
    @Test
    void generatePassword() {
        PasswordGenerator generator = new PasswordGenerator();
        record Check(char startValue, String password) {}
        Check[] checks = new Check[] {
            new Check('A', "ABCDEFGH"),
            new Check('C', "CDEFGHIJ"),
            new Check('v', "vwxyzABC"),
        };
        for(var check : checks) {
            generator.setRandom(new MockRandom(check.startValue));
            assertEquals(check.password, generator.generatePassword());
        }
    }
}
