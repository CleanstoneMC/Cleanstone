package rocks.cleanstone.net.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class SecurityUtils {

    private static SecureRandom secureRandom = new SecureRandom();

    private SecurityUtils() {
    }

    public static KeyPair generateKeyPair(int keySize) {
        KeyPairGenerator keyGen;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Cannot initialize RSA KeyPairGenerator", e);
        }
        keyGen.initialize(keySize);
        return keyGen.genKeyPair();
    }

    public static byte[] decryptRSA(byte[] data, PrivateKey privateKey) {
        try {
            Cipher rsaCipher = Cipher.getInstance("RSA");
            rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
            return rsaCipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            throw new RuntimeException("Cannot decrypt RSA", e);
        }
    }

    public static byte[] generateRandomToken(int length) {
        byte[] token = new byte[length];
        secureRandom.nextBytes(token);
        return token;
    }

    public static Key generateX509Key(Key baseKey) {
        Key x509Key;
        try {
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(baseKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            x509Key = keyFactory.generatePublic(encodedKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Cannot generate X509 Key", e);
        }
        return x509Key;
    }
}