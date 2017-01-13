package org.fabasoad.crypto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author efabizhevsky
 * @date 1/13/2017.
 */
public class BCryptTest {

    @Test
    public void testDecrypt() {
        String salt = CryptoUtils.BCrypt.getSalt();
        String plainText = "test string";

        String encryptedText1 = CryptoUtils.BCrypt.encrypt(plainText, salt);
        assertTrue(CryptoUtils.BCrypt.matches(plainText, encryptedText1));

        String encryptedText2 = CryptoUtils.BCrypt.encrypt(plainText, salt);
        assertEquals(encryptedText1, encryptedText2);
    }
}
