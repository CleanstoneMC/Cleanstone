package rocks.cleanstone.net.minecraft.login;

import com.google.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFuture;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.minecraft.HandshakeListener;
import rocks.cleanstone.net.minecraft.packet.data.Text;
import rocks.cleanstone.net.minecraft.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.DisconnectLoginPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.LoginSuccessPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.SetCompressionPacket;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.utils.SecurityUtils;
import rocks.cleanstone.net.utils.UUIDUtils;

public class LoginManager {

    private final Map<Connection, LoginData> connectionLoginDataMap = Maps.newConcurrentMap();
    private final SessionServerRequester sessionServerRequester;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Networking networking;
    private final PublicKey publicKey;
    private final PrivateKey privateKey;
    @Autowired
    private HandshakeListener handshakeListener;
    @Autowired
    private LoginStartListener loginStartListener;
    @Autowired
    private EncryptionResponseListener encryptionResponseListener;

    public LoginManager(Networking networking, SessionServerRequester sessionServerRequester) {
        this.networking = networking;
        this.sessionServerRequester = sessionServerRequester;
        publicKey = networking.getKeyPair().getPublic();
        privateKey = networking.getKeyPair().getPrivate();
    }

    public void startLogin(Connection connection, String playerName) {
        byte[] verifyToken = SecurityUtils.generateRandomToken(4);
        LoginData loginData = new LoginData(verifyToken, playerName);
        connectionLoginDataMap.put(connection, loginData);
        connection.sendPacket(LoginCrypto.constructEncryptionRequest(loginData, publicKey));
    }

    public void finishLogin(Connection connection, UUID uuid, String accountName,
                            SessionServerResponse.Property textures) {
        if (connectionLoginDataMap.remove(connection) == null)
            throw new IllegalStateException("Cannot finish login before it has started");
        SetCompressionPacket setCompressionPacket = new SetCompressionPacket(Short.MAX_VALUE);
        LoginSuccessPacket loginSuccessPacket = new LoginSuccessPacket(uuid, accountName);
        connection.sendPacket(setCompressionPacket);
        connection.setCompressionEnabled(true);
        connection.sendPacket(loginSuccessPacket);
        connection.setProtocolState(VanillaProtocolState.PLAY);

        //connection.sendPacket(new JoinGamePacket(0, 0, Dimension.OVERWORLD, Difficulty.EASY, LevelType.DEFAULT, false));
        //connection.sendPacket(new SpawnPositionPacket(new Position(0, 50, 0, null)));
        //connection.sendPacket(new PlayerAbilitiesPacket((byte) 0, 4, 4));
        //connection.sendPacket(new PlayerPositionAndLookPacket(0, 0, 0, 0, 0, 0, 0));
        //connection.sendPacket(new DisconnectPacket(Text.fromPlain("Kicked.")));
        // TODO Initialize and handle OnlinePlayer
    }

    public void stopLogin(Connection connection, Text reason) {
        connection.close(new DisconnectLoginPacket(reason));
        connectionLoginDataMap.remove(connection);
    }

    void onEncryptionResponse(Connection connection,
                              EncryptionResponsePacket encryptionResponsePacket) {
        LoginData loginData = connectionLoginDataMap.get(connection);
        if (loginData == null) throw new IllegalStateException("Connection hasnt started login process");
        SecretKey key = LoginCrypto.validateEncryptionResponse(loginData, privateKey, encryptionResponsePacket);
        connection.setSharedSecret(key);
        connection.setEncryptionEnabled(true);
        validateMinecraftSession(connection, loginData);
    }

    private void validateMinecraftSession(Connection connection, LoginData loginData) {
        logger.info("Pre validate session");
        ListenableFuture<SessionServerResponse> responseResult =
                sessionServerRequester.request(connection, loginData, publicKey);
        responseResult.addCallback((response) -> {
            UUID uuid = UUIDUtils.fromStringWithoutHyphens(response.getId());
            String name = response.getName();
            logger.info("Player " + name + " (" + uuid.toString() + ") logged in");
            SessionServerResponse.Property textures = response.getProperties()[0];
            finishLogin(connection, uuid, name, textures);
        }, e -> {
            e.printStackTrace();
            stopLogin(connection, Text.fromPlain("Failed to validate session"));
        });
    }
}
