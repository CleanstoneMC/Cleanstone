package rocks.cleanstone.player;

import java.net.InetAddress;

import rocks.cleanstone.game.chat.message.Chat;
import rocks.cleanstone.game.command.CommandSender;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.packet.data.Text;
import rocks.cleanstone.net.minecraft.packet.enums.ChatPosition;
import rocks.cleanstone.net.minecraft.packet.outbound.OutChatMessagePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.DisconnectPacket;
import rocks.cleanstone.net.packet.Packet;

public class OnlinePlayer extends AbstractPlayer implements CommandSender {

    private final Connection connection;
    private Human entity;

    public OnlinePlayer(PlayerID id, Connection connection) {
        super(id);
        this.connection = connection;
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
    public void kick(Text reason) {
        connection.close(new DisconnectPacket(reason));
    }

    @Override
    public Human getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Human entity) {
        this.entity = entity;
    }

    @Override
    public void sendMessage(Chat message) {
        connection.sendPacket(new OutChatMessagePacket(message, ChatPosition.CHAT));
    }

    @Override
    public void sendMessage(String message) {
        sendMessage(new Chat(message));
    }
}
