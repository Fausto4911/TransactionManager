package transaction;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;



public class AES {

    private static String algorithm = "RC4";
    private static String key = "MTFiZWM1YjgYWNkM";

//    public static byte[] encrypt(String toEncrypt) throws Exception {
//        // create a binary key from the argument key (seed)
//        SecureRandom sr = new SecureRandom(key.getBytes());
//        KeyGenerator kg = KeyGenerator.getInstance(algorithm);
//        kg.init(sr);
//        SecretKey sk = kg.generateKey();
//
//        // create an instance of cipher
//        Cipher cipher = Cipher.getInstance(algorithm);
//
//        // initialize the cipher with the key
//        cipher.init(Cipher.ENCRYPT_MODE, sk);
//
//        // enctypt!
//        byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
//
//        return encrypted;
//    }
//
//    public static String decrypt(byte[] toDecrypt) throws Exception {
//        // create a binary key from the argument key (seed)
//        SecureRandom sr = new SecureRandom(key.getBytes());
//        KeyGenerator kg = KeyGenerator.getInstance(algorithm);
//        kg.init(sr);
//        SecretKey sk = kg.generateKey();
//
//        // do the decryption with that key
//        Cipher cipher = Cipher.getInstance(algorithm);
//        cipher.init(Cipher.DECRYPT_MODE, sk);
//        byte[] decrypted = cipher.doFinal(toDecrypt);
//
//        return new String(decrypted);
//    }

    public static String encrypt(String value) {
        try {
            SecretKeySpec rc4Key = new SecretKeySpec(key.getBytes(), algorithm);
            Cipher rc4 = Cipher.getInstance(algorithm);

            rc4.init(Cipher.ENCRYPT_MODE, rc4Key);
            byte[] encrypted = rc4.update(value.getBytes());

            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(encrypted), "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            byte[] decodeValue = org.apache.commons.codec.binary.Base64.decodeBase64(encrypted.getBytes("UTF-8"));

            SecretKeySpec rc4Key = new SecretKeySpec(key.getBytes(), algorithm);

            Cipher rc4 = Cipher.getInstance(algorithm);
            rc4.init(Cipher.DECRYPT_MODE, rc4Key);

            byte[] dec = rc4.doFinal(decodeValue);

            return new String(dec, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}