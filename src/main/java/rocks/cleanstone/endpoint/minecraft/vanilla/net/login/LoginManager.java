package rocks.cleanstone.endpoint.minecraft.vanilla.net.login;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.MinecraftNetworking;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.login.event.AsyncLoginEvent;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.login.event.AsyncLoginSuccessEvent;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.DisconnectLoginPacket;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.LoginSuccessPacket;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.SetCompressionPacket;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.protocol.VanillaProtocolState;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.utils.SecurityUtils;
import rocks.cleanstone.net.utils.UUIDUtils;
import rocks.cleanstone.player.UserProperty;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;

@Slf4j
@Component
public class LoginManager {

    private final Map<Connection, LoginData> connectionLoginDataMap = Maps.newConcurrentMap();
    private final SessionServerRequester sessionServerRequester;
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

    void startLogin(Connection connection, String playerName) {
        Preconditions.checkState(!connectionLoginDataMap.containsKey(connection), "Login already started");
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

    private void finishLogin(Connection connection, UUID uuid, String accountName, UserProperty[] properties) {
        if (connectionLoginDataMap.remove(connection) == null || connection.isClosed()) {
            return;
        }

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

        log.info("Player " + accountName + " (" + uuid.toString() + ") logged in");
        connection.setProtocolState(VanillaProtocolState.PLAY);
        CleanstoneServer.publishEvent(
                new AsyncLoginSuccessEvent(connection, uuid, accountName, userProperties));
    }

    void stopLogin(Connection connection, Text reason) {
        if (connectionLoginDataMap.remove(connection) == null) {
            throw new IllegalArgumentException("Login has not started");
        }
        connection.close(new DisconnectLoginPacket(reason));
    }

    void handleEncryptionResponse(Connection connection,
                                  EncryptionResponsePacket encryptionResponsePacket) {
        LoginData loginData = connectionLoginDataMap.get(connection);
        if (loginData == null) {
            throw new IllegalArgumentException("Login has not started");
        }
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
                log.error("Error occurred while finishing login", e);
            }
        }, e -> {
            try {
                log.error("Error occurred while requesting session servers", e);
                stopLogin(connection, Text.of("Failed to validate session"));
            } catch (Exception e2) {
                log.error("Error occurred while stopping login", e2);
            }
        });
    }
}
