package rocks.cleanstone.net.minecraft.login;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.MinecraftNetworking;
import rocks.cleanstone.net.minecraft.login.event.AsyncLoginEvent;
import rocks.cleanstone.net.minecraft.login.event.AsyncLoginSuccessEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.DisconnectLoginPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.LoginSuccessPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.SetCompressionPacket;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.utils.SecurityUtils;
import rocks.cleanstone.net.utils.UUIDUtils;
import rocks.cleanstone.player.UserProperty;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;

@Component
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
        final byte[] verifyToken = SecurityUtils.generateRandomToken(4);
        final LoginData loginData = new LoginData(verifyToken, playerName);
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

        final Collection<UserProperty> userProperties = new ArrayList<>(Arrays.asList(properties));

        final AsyncLoginEvent event = CleanstoneServer.publishEvent(
                new AsyncLoginEvent(connection, uuid, accountName, userProperties));
        if (event.isCancelled()) {
            stopLogin(connection, event.getKickReason());
            return;
        }
        final SetCompressionPacket setCompressionPacket = new SetCompressionPacket(0);
        final LoginSuccessPacket loginSuccessPacket = new LoginSuccessPacket(uuid, accountName);
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
        final LoginData loginData = connectionLoginDataMap.get(connection);
        if (loginData == null || connection.isClosed()) return;
        final SecretKey key = LoginCrypto.validateEncryptionResponse(loginData, privateKey, encryptionResponsePacket);
        connection.setSharedSecret(key);
        connection.setEncryptionEnabled(true);
        validateMinecraftSession(connection, loginData);
    }

    private void validateMinecraftSession(Connection connection, LoginData loginData) {
        final ListenableFuture<SessionServerResponse> responseResult =
                sessionServerRequester.request(connection, loginData, publicKey);
        responseResult.addCallback((response) -> {
            try {
                final UUID uuid = UUIDUtils.fromStringWithoutHyphens(response.getId());
                final String name = response.getName();
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
