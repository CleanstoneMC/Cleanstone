package rocks.cleanstone.player;

import java.net.InetAddress;
import java.util.Collection;
import java.util.concurrent.Future;

import rocks.cleanstone.game.Identity;
import rocks.cleanstone.game.chat.ChatPosition;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.DisconnectPacket;
import rocks.cleanstone.net.packet.outbound.OutChatMessagePacket;

public class OnlinePlayer extends AbstractPlayer {

    private final Connection connection;
    private final Collection<UserProperty> userProperties;

    public OnlinePlayer(Identity id, Connection connection, Collection<UserProperty> userProperties) {
        super(id);
        this.connection = connection;
        this.userProperties = userProperties;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void sendPacket(Packet packet) {
        connection.sendPacket(packet);
    }

    @Override
    public InetAddress getAddress() {
        return connection.getAddress();
    }

    @Override
    public int getPing() {
        return 0; // TODO Fetch player ping
    }

    @Override
    public Future<Void> kick(Text reason) {
        return connection.close(new DisconnectPacket(reason));
    }

    @Override
    public Collection<UserProperty> getUserProperties() {
        return userProperties;
    }

    @Override
    public void sendRawMessage(Text message) {
        connection.sendPacket(new OutChatMessagePacket(message, ChatPosition.CHAT));
    }

    @Override
    public void sendRawMessage(String message) {
        sendRawMessage(Text.of(message));
    }

    @Override
    public void sendMessage(String messageID, Object... args) {
        sendRawMessage(Text.ofLocalized(messageID, getLocale(), args));
    }
}
