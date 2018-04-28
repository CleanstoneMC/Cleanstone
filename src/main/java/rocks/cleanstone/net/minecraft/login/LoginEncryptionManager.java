package rocks.cleanstone.net.minecraft.login;

import java.security.PrivateKey;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.EncryptionRequestPacket;
import rocks.cleanstone.net.utils.SecurityUtils;

public class LoginEncryptionManager {

    private final LoginManager loginManager;

    public LoginEncryptionManager(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    public void sendEncryptionRequest(Connection connection, LoginData loginData) {
        String serverID = ""; // empty as of MC 1.7.X
        byte[] encodedPublicKey = SecurityUtils.generateX509Key(
                loginManager.getNetworking().getKeyPair().getPublic()).getEncoded();

        EncryptionRequestPacket encryptionRequestPacket = new EncryptionRequestPacket(serverID,
                encodedPublicKey, loginData.getVerifyToken());
        loginManager.getNetworking().sendPacket(connection, encryptionRequestPacket);
    }

    public void validateEncryptionResponse(Connection connection, LoginData loginData,
                                           EncryptionResponsePacket responsePacket) {
        byte[] sharedSecretData = responsePacket.getSharedSecret();
        byte[] verifyTokenData = responsePacket.getVerifyToken();
        PrivateKey privateKey = loginManager.getNetworking().getKeyPair().getPrivate();

        SecretKey sharedSecret = new SecretKeySpec(
                SecurityUtils.decryptRSA(sharedSecretData, privateKey), "AES");

        byte[] verifyToken = SecurityUtils.decryptRSA(verifyTokenData, privateKey);
        if (verifyToken != loginData.getVerifyToken()) {
            throw new RuntimeException("Invalid verifyToken");
        }

        connection.setSharedSecret(sharedSecret);
        connection.setEncryptionEnabled(true);
    }
}
