package org.example;

import org.example.utilities.JwtUtil;
import org.example.utilities.PasswordEncrypter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class IndexTest {
    Index index;

    @BeforeEach
    void setUp() {
        index = new Index();

        index.addUsers("anna", "losen");
        index.addUsers("berit", "123456");
        index.addUsers("kalle", "password");
    }

    @Test
    public void testEncryptedPasswordSuccess() {
        assertTrue(PasswordEncrypter.generateNewHashPassword(""));
    }

    @ParameterizedTest
    @CsvSource({"anna,losen", "berit,123456", "kalle,password"})
    void testVerifyUsersWithTokenSuccess(String username, String password) throws LoginFailException {
        assertDoesNotThrow(() -> index.logIn(username, password));
        String token = index.logIn(username, password);

        assertFalse(token.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({"anna,losen", "berit,123456", "kalle,password"})
    void testVerifyTokenSuccess(String username, String password) throws LoginFailException {
        assertDoesNotThrow(() -> index.logIn(username, password));
        String token = index.logIn(username, password);

        assertTrue(JwtUtil.validateToken(username, token));
    }
}