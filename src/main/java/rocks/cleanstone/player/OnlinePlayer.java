package rocks.cleanstone.player;

import java.net.InetAddress;
import java.util.Collection;

import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.game.chat.ChatPosition;
import rocks.cleanstone.net.packet.outbound.DisconnectPacket;
import rocks.cleanstone.net.packet.outbound.OutChatMessagePacket;

public class OnlinePlayer extends AbstractPlayer {

    private final Connection connection;
    private final Collection<UserProperty> userProperties;

    public OnlinePlayer(PlayerID id, Connection connection, Collection<UserProperty> userProperties) {
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
    public void kick(Text reason) {
        connection.close(new DisconnectPacket(reason));
    }

    @Override
    public Collection<UserProperty> getUserProperties() {
        return userProperties;
    }

    @Override
    public void sendMessage(Text message) {
        connection.sendPacket(new OutChatMessagePacket(message, ChatPosition.CHAT));
    }

    @Override
    public void sendMessage(String message) {
        sendMessage(Text.of(message));
    }
}
