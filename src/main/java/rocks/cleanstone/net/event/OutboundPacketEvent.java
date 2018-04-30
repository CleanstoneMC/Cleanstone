package rocks.cleanstone.net.event;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.packet.Packet;

public class OutboundPacketEvent {

    private final Networking networking;
    private final Packet packet;
    private final Connection connection;
    private boolean cancelled = false;

    public OutboundPacketEvent(Packet packet, Connection connection, Networking networking) {
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

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
