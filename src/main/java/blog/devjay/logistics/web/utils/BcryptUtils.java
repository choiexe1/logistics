package blog.devjay.logistics.web.utils;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptUtils {
    private static final String SALT = BCrypt.gensalt();

    public static String hashPw(String password) {
        return BCrypt.hashpw(password, SALT);
    }

    public static boolean checkPw(String plain, String hashedPassword) {
        return BCrypt.checkpw(plain, hashedPassword);
    }
}
