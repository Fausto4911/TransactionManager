package transaction;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private static String algorithm = "RC4";
    private static String key = "MTFiZWM1YjgYWNkM";

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