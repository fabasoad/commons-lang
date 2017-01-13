package org.fabasoad.crypto;

import static org.fabasoad.crypto.BCrypt.checkpw;
import static org.fabasoad.crypto.BCrypt.gensalt;
import static org.fabasoad.crypto.BCrypt.hashpw;

/**
 * @author efabizhevsky
 * @date 1/13/2017.
 */
public class CryptoUtils {

    public static class BCrypt {

        public static String encrypt(String value) {
            return hashpw(value, gensalt(12));
        }

        public static boolean matches(String plain, String encrypted) {
            return checkpw(plain, encrypted);
        }
    }
}
