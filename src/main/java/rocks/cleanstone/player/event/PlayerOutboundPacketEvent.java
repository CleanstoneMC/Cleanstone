package rocks.cleanstone.player.event;

import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.player.Player;

public class PlayerOutboundPacketEvent extends CancellableEvent {

    private final Networking networking;
    private final Packet packet;
    private final Connection connection;
    private final Player player;

    public PlayerOutboundPacketEvent(Packet packet, Connection connection, Player player,
                                     Networking networking) {
        this.packet = packet;
        this.connection = connection;
        this.player = player;
        this.networking = networking;
    }

    public Networking getNetworking() {
        return networking;
    }

    public Connection getConnection() {
        return connection;
    }

    public Packet getPacket() {
        return packet;
    }

    public Player getPlayer() {
        return player;
    }
}
