package io.github.fabasoad.crypto;

import static io.github.fabasoad.crypto.BCrypt.checkpw;
import static io.github.fabasoad.crypto.BCrypt.genSalt;
import static io.github.fabasoad.crypto.BCrypt.hashpw;

public class CryptoUtils {

    public static class BCrypt {

        public static String genSalt(int logRounds) {
            return io.github.fabasoad.crypto.BCrypt.genSalt(logRounds);
        }

        public static String genSalt() {
            return genSalt(12);
        }

        public static String encrypt(String value, String salt) {
            return hashpw(value, salt);
        }

        public static boolean matches(String plain, String encrypted) {
            return checkpw(plain, encrypted);
        }
    }
}
