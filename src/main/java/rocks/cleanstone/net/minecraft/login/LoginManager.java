package rocks.cleanstone.net.minecraft.login;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFuture;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.MinecraftNetworking;
import rocks.cleanstone.net.minecraft.login.event.AsyncLoginEvent;
import rocks.cleanstone.net.minecraft.login.event.AsyncLoginSuccessEvent;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.packet.outbound.DisconnectLoginPacket;
import rocks.cleanstone.net.packet.outbound.LoginSuccessPacket;
import rocks.cleanstone.net.packet.outbound.SetCompressionPacket;
import rocks.cleanstone.net.utils.SecurityUtils;
import rocks.cleanstone.net.utils.UUIDUtils;
import rocks.cleanstone.player.UserProperty;

public class LoginManager {

    private final Map<Connection, LoginData> connectionLoginDataMap = Maps.newConcurrentMap();
    private final SessionServerRequester sessionServerRequester;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final MinecraftNetworking networking;
    private final PublicKey publicKey;
    private final PrivateKey privateKey;
    private final boolean onlineMode;

    @Autowired
    public LoginManager(MinecraftNetworking networking, SessionServerRequester sessionServerRequester) {
        this.networking = networking;
        this.sessionServerRequester = sessionServerRequester;
        this.onlineMode = networking.getMinecraftConfig().isOnlineMode();
        publicKey = networking.getKeyPair().getPublic();
        privateKey = networking.getKeyPair().getPrivate();
    }

    public void startLogin(Connection connection, String playerName) {
        byte[] verifyToken = SecurityUtils.generateRandomToken(4);
        LoginData loginData = new LoginData(verifyToken, playerName);
        connectionLoginDataMap.put(connection, loginData);

        if (!onlineMode) {
            finishLogin(connection, UUID.nameUUIDFromBytes(("OfflinePlayer:" + playerName)
                    .getBytes(Charsets.UTF_8)), playerName, null);
            return;
        }
        connection.sendPacket(LoginCrypto.constructEncryptionRequest(loginData, publicKey));
    }

    public void finishLogin(Connection connection, UUID uuid, String accountName, UserProperty[] properties) {
        if (connectionLoginDataMap.remove(connection) == null || connection.isClosed()) return;

        if (properties == null) {
            properties = new UserProperty[0];//Fix for Offline mode nullpointer Exception
        }

        Collection<UserProperty> userProperties = new ArrayList<>(Arrays.asList(properties));

        AsyncLoginEvent event = CleanstoneServer.publishEvent(
                new AsyncLoginEvent(connection, uuid, accountName, userProperties));
        if (event.isCancelled()) {
            stopLogin(connection, event.getKickReason());
            return;
        }
        SetCompressionPacket setCompressionPacket = new SetCompressionPacket(0);
        LoginSuccessPacket loginSuccessPacket = new LoginSuccessPacket(uuid, accountName);
        //connection.sendPacket(setCompressionPacket);
        //connection.setCompressionEnabled(true); TODO Fix compression handler
        connection.sendPacket(loginSuccessPacket);

        logger.info("Player " + accountName + " (" + uuid.toString() + ") logged in");
        connection.setProtocolState(VanillaProtocolState.PLAY);
        CleanstoneServer.publishEvent(
                new AsyncLoginSuccessEvent(connection, uuid, accountName, userProperties));
    }

    public void stopLogin(Connection connection, Text reason) {
        connection.close(new DisconnectLoginPacket(reason));
        connectionLoginDataMap.remove(connection);
    }

    void onEncryptionResponse(Connection connection,
                              EncryptionResponsePacket encryptionResponsePacket) {
        LoginData loginData = connectionLoginDataMap.get(connection);
        if (loginData == null || connection.isClosed()) return;
        SecretKey key = LoginCrypto.validateEncryptionResponse(loginData, privateKey, encryptionResponsePacket);
        connection.setSharedSecret(key);
        connection.setEncryptionEnabled(true);
        validateMinecraftSession(connection, loginData);
    }

    private void validateMinecraftSession(Connection connection, LoginData loginData) {
        ListenableFuture<SessionServerResponse> responseResult =
                sessionServerRequester.request(connection, loginData, publicKey);
        responseResult.addCallback((response) -> {
            try {
                UUID uuid = UUIDUtils.fromStringWithoutHyphens(response.getId());
                String name = response.getName();
                finishLogin(connection, uuid, name, response.getProperties());
            } catch (Exception e) {
                logger.error("Error occurred while finishing login", e);
            }
        }, e -> {
            try {
                logger.error("Error occurred while requesting session servers", e);
                stopLogin(connection, Text.of("Failed to validate session"));
            } catch (Exception e2) {
                logger.error("Error occurred while stopping login", e2);
            }
        });
    }
}
