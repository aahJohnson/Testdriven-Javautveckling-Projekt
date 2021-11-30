package org.example.utilities;

import org.example.LoginFailException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class PasswordEncrypter {
    public static boolean generateNewHashPassword(String password) throws Exception {
        String salt = generateSalt(512).get();
        String saltKey = hashPassword(password,salt).get();

        return verifyPassword(password,saltKey,salt);
    }

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    public static Optional<String> hashPassword(String password, String salt) throws Exception {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory secretKey = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = secretKey.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } finally {
            spec.clearPassword();
        }
    }

    private static final SecureRandom RAND = new SecureRandom();

    public static Optional<String> generateSalt (final int length) {
        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }

    public static boolean verifyPassword (String password, String key, String salt) throws Exception {
        Optional<String> optionalEncrypt = hashPassword(password, salt);
        if (!optionalEncrypt.isPresent()) return false;
        return optionalEncrypt.get().equals(key);
    }
}