package sis.util;

import java.util.Random;

public class PasswordGenerator {
    public static final char LOW_END_PASSWORD_CHAR = 'A';
    public static final char HIGH_END_PASSWORD_CHAR = 'z' + 1;
    private static final int PASSWORD_LENGTH = 8;

    private Random random = new Random();
    public void setRandom(Random random) {
        this.random = random;
    }

    public String generatePassword() {
        StringBuilder builder = new StringBuilder(PASSWORD_LENGTH);
        for(int i =0; i< PASSWORD_LENGTH; ++i) {
            final var max = HIGH_END_PASSWORD_CHAR - LOW_END_PASSWORD_CHAR;
            final char randomChar = (char)(random.nextInt(max) + LOW_END_PASSWORD_CHAR);
            builder.append(randomChar);
        }
        return builder.toString();
    }
}
