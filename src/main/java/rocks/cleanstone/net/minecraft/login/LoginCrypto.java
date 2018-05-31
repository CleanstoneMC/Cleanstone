package rocks.cleanstone.net.minecraft.login;

import com.google.common.base.Charsets;
import rocks.cleanstone.net.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.packet.outbound.EncryptionRequestPacket;
import rocks.cleanstone.net.utils.SecurityUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

public class LoginCrypto {

    public static EncryptionRequestPacket constructEncryptionRequest(LoginData loginData, PublicKey publicKey) {
        String serverID = ""; // empty as of MC 1.7.X
        byte[] encodedPublicKey = SecurityUtils.generateX509Key(publicKey).getEncoded();
        return new EncryptionRequestPacket(serverID, encodedPublicKey, loginData.getVerifyToken());
    }

    public static SecretKey validateEncryptionResponse(LoginData loginData, PrivateKey privateKey,
                                                       EncryptionResponsePacket responsePacket) {
        byte[] sharedSecretData = responsePacket.getSharedSecret();
        byte[] verifyTokenData = responsePacket.getVerifyToken();

        SecretKey sharedSecret = new SecretKeySpec(
                SecurityUtils.decryptRSA(sharedSecretData, privateKey), "AES");

        byte[] verifyToken = SecurityUtils.decryptRSA(verifyTokenData, privateKey);
        if (!Arrays.equals(verifyToken, loginData.getVerifyToken())) {
            throw new RuntimeException("Invalid verifyToken");
        }
        return sharedSecret;
    }

    public static String generateAuthHash(SecretKey sharedSecret, PublicKey publicKey) {
        String hash;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update("".getBytes(Charsets.US_ASCII)); // Server ID is empty
            digest.update(sharedSecret.getEncoded());
            digest.update(publicKey.getEncoded());
            hash = new BigInteger(digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate authentication hash", e);
        }
        return hash;
    }
}
