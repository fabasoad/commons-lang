package org.fabasoad.crypto;

import static org.fabasoad.crypto.BCrypt.checkpw;
import static org.fabasoad.crypto.BCrypt.gensalt;
import static org.fabasoad.crypto.BCrypt.hashpw;

/**
 * @author efabizhevsky
 * @date 1/13/2017.
 */
public class CryptoUtils {

    static class BCrypt {

        static String decrypt(String value) {
            return hashpw(value, gensalt(12));
        }

        static boolean matches(String plain, String decrypted) {
            return checkpw(plain, decrypted);
        }
    }
}
