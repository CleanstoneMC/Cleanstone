package rocks.cleanstone.net.minecraft.login;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import rocks.cleanstone.game.world.region.Position;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.MinecraftNetworking;
import rocks.cleanstone.net.minecraft.packet.data.Chat;
import rocks.cleanstone.net.minecraft.packet.enums.Difficulty;
import rocks.cleanstone.net.minecraft.packet.enums.Dimension;
import rocks.cleanstone.net.minecraft.packet.enums.LevelType;
import rocks.cleanstone.net.minecraft.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.*;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.utils.SecurityUtils;
import rocks.cleanstone.net.utils.UUIDUtils;

import java.util.Map;
import java.util.UUID;

public class LoginManager {

    private final Map<Connection, LoginData> connectionLoginDataMap = Maps.newConcurrentMap();
    private final LoginEncryptionManager loginEncryptionManager;
    private final SessionServerRequester sessionServerRequester;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private MinecraftNetworking networking;

    @Autowired
    private HandshakeListener handshakeListener;
    @Autowired
    private LoginStartListener loginStartListener;
    @Autowired
    private EncryptionResponseListener encryptionResponseListener;

    public LoginManager() {
        loginEncryptionManager = new LoginEncryptionManager(this);
        sessionServerRequester = new SessionServerRequester(this);
    }

    public void init() {
        loginStartListener.setLoginManager(this);
        encryptionResponseListener.setLoginManager(this);
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
        connection.setCompressionEnabled(true);
        connection.sendPacket(loginSuccessPacket);
        connection.setProtocolState(VanillaProtocolState.PLAY);

        //connection.sendPacket(new JoinGamePacket(0, 0, Dimension.OVERWORLD, Difficulty.EASY, LevelType.DEFAULT, false));
        //connection.sendPacket(new SpawnPositionPacket(new Position(0, 50, 0, null)));
        //connection.sendPacket(new PlayerAbilitiesPacket((byte) 0, 4, 4));
        //connection.sendPacket(new PlayerPositionAndLookPacket(0, 0, 0, 0, 0, 0, 0));
        //connection.sendPacket(new DisconnectPacket(new Chat("{\"text\": \"Bye\"}")));
        // TODO Initialize and handle OnlinePlayer
    }

    public void stopLogin(Connection connection, Chat reason) {
        connection.close(new DisconnectLoginPacket(reason));
        connectionLoginDataMap.remove(connection);
    }

    void onEncryptionResponse(Connection connection,
                              EncryptionResponsePacket encryptionResponsePacket) {
        LoginData loginData = connectionLoginDataMap.get(connection);
        if (loginData == null) throw new IllegalStateException("Connection hasnt started login process");
        loginEncryptionManager.validateEncryptionResponse(connection, loginData, encryptionResponsePacket);
        validateMinecraftSession(connection, loginData);
    }

    private void validateMinecraftSession(Connection connection, LoginData loginData) {
        AsyncResult<SessionServerResponse> responseResult =
                sessionServerRequester.request(connection, loginData);
        responseResult.addCallback((response) -> {
            UUID uuid = UUIDUtils.fromStringWithoutHyphens(response.getId());
            String name = response.getName();
            logger.info("Player " + name + " (" + uuid.toString() + ") logged in");
            SessionServerResponse.Property textures = response.getProperties()[0];
            finishLogin(connection, uuid, name, textures);
        }, e -> {
            e.printStackTrace();
            stopLogin(connection, new Chat("TODO: JSON reason"));
        });
    }

    public MinecraftNetworking getNetworking() {
        return networking;
    }

    public void setNetworking(MinecraftNetworking networking) {
        this.networking = networking;
    }
}
