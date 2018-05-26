package rocks.cleanstone.net.event;

import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.packet.Packet;

public class InboundPacketEvent extends CancellableEvent {

    private final Networking networking;
    private final Packet packet;
    private final Connection connection;

    public InboundPacketEvent(Packet packet, Connection connection, Networking networking) {
        this.packet = packet;
        this.connection = connection;
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
}
