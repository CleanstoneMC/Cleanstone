package rocks.cleanstone.player;

import lombok.Getter;
import rocks.cleanstone.game.Identity;
import rocks.cleanstone.game.chat.ChatPosition;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.packet.outbound.DisconnectPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.OutChatMessagePacket;
import rocks.cleanstone.net.packet.Packet;

import java.net.InetAddress;
import java.util.Collection;
import java.util.concurrent.Future;

public class OnlinePlayer extends AbstractPlayer {

    @Getter
    private final Connection connection;
    @Getter
    private final Collection<UserProperty> userProperties;

    public OnlinePlayer(Identity id, Connection connection, Collection<UserProperty> userProperties) {
        super(id);
        this.connection = connection;
        this.userProperties = userProperties;
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
