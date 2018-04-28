package rocks.cleanstone.net.minecraft.login;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.UUID;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.minecraft.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.LoginSuccessPacket;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.utils.SecurityUtils;

public class LoginManager {

    private final Networking networking;
    private final Map<Connection, LoginData> connectionLoginDataMap = Maps.newConcurrentMap();
    private final LoginEncryptionManager loginEncryptionManager;

    public LoginManager(Networking networking) {
        this.networking = networking;
        loginEncryptionManager = new LoginEncryptionManager(this);

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

    public void finishLogin(Connection connection, UUID uuid, String accountName) {
        if (connectionLoginDataMap.remove(connection) == null)
            throw new IllegalStateException("Cannot finish login before it has started");

        LoginSuccessPacket loginSuccessPacket = new LoginSuccessPacket(uuid, accountName);
        networking.sendPacket(connection, loginSuccessPacket);
        connection.setProtocolState(VanillaProtocolState.PLAY);
        // TODO Initialize and handle OnlinePlayer
    }

    void onEncryptionResponse(Connection connection,
                              EncryptionResponsePacket encryptionResponsePacket) {
        LoginData loginData = connectionLoginDataMap.get(connection);
        if (loginData == null) throw new IllegalStateException("Connection hasnt started login process");
        loginEncryptionManager.validateEncryptionResponse(connection, loginData, encryptionResponsePacket);
        validateMinecraftSession(connection);
    }

    private void validateMinecraftSession(Connection connection) {
        // TODO validate minecraft session using session servers
        finishLogin(connection, null, null);
    }

    public Networking getNetworking() {
        return networking;
    }
}
