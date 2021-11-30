package org.example;

import org.example.enums.Permissions;
import org.example.enums.Resource;
import org.example.utilities.JwtUtil;
import org.example.utilities.PasswordEncrypter;
import org.example.utilities.PermissionUtils;
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

        index.addUsers("anna", "losen", Permissions.READ_WRITE_EXECUTE, Permissions.EXECUTE);
        index.addUsers("berit", "123456", Permissions.READ_EXECUTE, Permissions.WRITE);
        index.addUsers("kalle", "password", Permissions.READ, Permissions.READ_WRITE);

        index.updateUserPermissionList();
    }

    @Test
    public void testEncryptedPasswordSuccess() throws Exception {
        assertTrue(PasswordEncrypter.generateNewHashPassword("123456"));
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

    @Test
    void testUserPermissionSuccess() {
        index.setTokenToAllUsers();
        index.userList.forEach((username, user) -> {
            assertNotNull(PermissionUtils.getUserPermissions(user.getToken(), Resource.ACCOUNT));
        });

        index.userList.forEach((username, user) -> {
            assertNotNull(PermissionUtils.getUserPermissions(user.getToken(), Resource.PROVISION_CALC));
        });
    }
}