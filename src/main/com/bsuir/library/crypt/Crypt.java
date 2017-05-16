package main.com.bsuir.library.crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Саша on 04.05.2017.
 */
public class Crypt {
    public String getCrypt(String text) throws NoSuchAlgorithmException {
        StringBuffer code = new StringBuffer();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte bytes[] = text.getBytes();
        byte digest[] = messageDigest.digest(bytes); //create code
        for (int i = 0; i < digest.length; ++i) {
            code.append(Integer.toHexString(0x0100 + (digest[i] & 0x00FF)).substring(1));
        }
        return String.valueOf(code);
    }
}
