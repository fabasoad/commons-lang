package org.fabasoad.crypto;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author efabizhevsky
 * @date 1/13/2017.
 */
public class BCryptTest {

    @Test
    public void testDecrypt() {
        String plainText = "test string";
        String decryptedText = CryptoUtils.BCrypt.decrypt(plainText);

        assertTrue(CryptoUtils.BCrypt.matches(plainText, decryptedText));
    }
}
