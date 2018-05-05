package rocks.cleanstone.core.player;

import java.net.InetAddress;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.packet.data.Text;
import rocks.cleanstone.net.minecraft.packet.outbound.DisconnectPacket;
import rocks.cleanstone.net.packet.Packet;

public class OnlinePlayer extends AbstractPlayer {

    private final Connection connection;

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
}
