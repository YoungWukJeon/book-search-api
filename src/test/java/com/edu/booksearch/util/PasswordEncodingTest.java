package com.edu.booksearch.util;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncodingTest {
    private PasswordEncoding passwordEncoding = new PasswordEncoding();;

    @Test
    void 암호화_인코딩() {
        String rawPassword = "testPass123!!";
        String encodedPassword = passwordEncoding.encode(rawPassword);

        System.out.println(rawPassword + " -> " + encodedPassword);
    }

    @Test
    void 암호화_비교_같을경우() {
        String rawPassword1 = "testPass123!!";
        String encodedPassword1 = passwordEncoding.encode(rawPassword1);

        String rawPassword2 = "testPass123!!";

        assertTrue(passwordEncoding.matches(rawPassword2, encodedPassword1));
    }

    @Test
    void 암호화_비교_다를경우() {
        String rawPassword1 = "testPass123!!";
        String encodedPassword1 = passwordEncoding.encode(rawPassword1);

        String rawPassword2 = "testPass125!!";

        assertFalse(passwordEncoding.matches(rawPassword2, encodedPassword1));
    }
}