package rocks.cleanstone.net.minecraft.login;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.minecraft.packet.data.Chat;
import rocks.cleanstone.net.minecraft.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.DisconnectLoginPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.LoginSuccessPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.SetCompressionPacket;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.utils.SecurityUtils;
import rocks.cleanstone.net.utils.UUIDUtils;

public class LoginManager {

    private final Networking networking;
    private final Map<Connection, LoginData> connectionLoginDataMap = Maps.newConcurrentMap();
    private final LoginEncryptionManager loginEncryptionManager;
    private final SessionServerRequester sessionServerRequester;

    public LoginManager(Networking networking) {
        this.networking = networking;
        loginEncryptionManager = new LoginEncryptionManager(this);
        sessionServerRequester = new SessionServerRequester(this);

        networking.registerPacketListener(new HandshakeListener(), MinecraftInboundPacketType.HANDSHAKE);
        networking.registerPacketListener(
                new LoginStartListener(this), MinecraftInboundPacketType.LOGIN_START);
        networking.registerPacketListener(
                new EncryptionResponseListener(this), MinecraftInboundPacketType.ENCRYPTION_RESPONSE);
    }

    public void startLogin(Connection connection, String playerName) {
        byte[] verifyToken = SecurityUtils.generateRandomToken(4);
        LoginData loginData = new LoginData(verifyToken, playerName);
        connectionLoginDataMap.put(connection, loginData);
        loginEncryptionManager.sendEncryptionRequest(connection, loginData);
    }

    public void finishLogin(Connection connection, UUID uuid, String accountName,
                            SessionServerResponse.Property textures) {
        if (connectionLoginDataMap.remove(connection) == null)
            throw new IllegalStateException("Cannot finish login before it has started");
        SetCompressionPacket setCompressionPacket = new SetCompressionPacket(Short.MAX_VALUE);
        LoginSuccessPacket loginSuccessPacket = new LoginSuccessPacket(uuid, accountName);
        connection.sendPacket(setCompressionPacket);
        connection.sendPacket(loginSuccessPacket);
        connection.setProtocolState(VanillaProtocolState.PLAY);
        // TODO Initialize and handle OnlinePlayer
    }

    public void stopLogin(Connection connection, Chat reason) {
        connection.close(new DisconnectLoginPacket(reason));
    }

    void onEncryptionResponse(Connection connection,
                              EncryptionResponsePacket encryptionResponsePacket) {
        LoginData loginData = connectionLoginDataMap.get(connection);
        if (loginData == null) throw new IllegalStateException("Connection hasnt started login process");
        loginEncryptionManager.validateEncryptionResponse(connection, loginData, encryptionResponsePacket);
        validateMinecraftSession(connection, loginData);
    }

    private void validateMinecraftSession(Connection connection, LoginData loginData) {
        CompletableFuture<SessionServerResponse> responseFuture =
                sessionServerRequester.request(connection, loginData);
        responseFuture.thenAccept((response) -> {
            UUID uuid = UUIDUtils.fromStringWithoutHyphens(response.getId());
            String name = response.getName();
            SessionServerResponse.Property textures = response.getProperties()[0];
            finishLogin(connection, uuid, name, textures);
        }).exceptionally(e -> {
            e.printStackTrace();
            stopLogin(connection, new Chat("TODO: JSON reason"));
            return null;
        });
    }

    public Networking getNetworking() {
        return networking;
    }
}
