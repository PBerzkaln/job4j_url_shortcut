package ru.job4j.shortcut.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class LoginAndUrlKeyGenerator {
    /**
     * numeral '0'
     */
    private static final int LEFT_LIMIT = 48;
    /**
     * letter 'z'
     */
    private static final int RIGHT_LIMIT = 122;
    private static final int TARGET_STRING_LENGTH = 5;

    private String generate() {
        Random random = new Random();
        return random.ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(TARGET_STRING_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String generateLogin(String siteName) {
        return siteName + generate();
    }

    public String generateKey() {
        return generate();
    }
}