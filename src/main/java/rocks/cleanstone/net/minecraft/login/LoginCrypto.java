package rocks.cleanstone.net.minecraft.login;

import com.google.common.base.Charsets;
import rocks.cleanstone.net.minecraft.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.EncryptionRequestPacket;
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
        final String serverID = ""; // empty as of MC 1.7.X
        final byte[] encodedPublicKey = SecurityUtils.generateX509Key(publicKey).getEncoded();
        return new EncryptionRequestPacket(serverID, encodedPublicKey, loginData.getVerifyToken());
    }

    public static SecretKey validateEncryptionResponse(LoginData loginData, PrivateKey privateKey,
                                                       EncryptionResponsePacket responsePacket) {
        final byte[] sharedSecretData = responsePacket.getSharedSecret();
        final byte[] verifyTokenData = responsePacket.getVerifyToken();

        final SecretKey sharedSecret = new SecretKeySpec(
                SecurityUtils.decryptRSA(sharedSecretData, privateKey), "AES");

        final byte[] verifyToken = SecurityUtils.decryptRSA(verifyTokenData, privateKey);
        if (!Arrays.equals(verifyToken, loginData.getVerifyToken())) {
            throw new RuntimeException("Invalid verifyToken");
        }
        return sharedSecret;
    }

    public static String generateAuthHash(SecretKey sharedSecret, PublicKey publicKey) {
        final String hash;
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-1");
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
