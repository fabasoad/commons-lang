package io.github.fabasoad.crypto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BCryptTest {

    @Test
    public void testEncrypt() {
        String salt = CryptoUtils.BCrypt.genSalt();
        String plainText = "test string";

        String encryptedText1 = CryptoUtils.BCrypt.encrypt(plainText, salt);
        assertTrue(CryptoUtils.BCrypt.matches(plainText, encryptedText1));

        String encryptedText2 = CryptoUtils.BCrypt.encrypt(plainText, salt);
        assertEquals(encryptedText1, encryptedText2);
    }
}
