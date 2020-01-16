package rocks.cleanstone.endpoint.cleanstone.net.packet;

import rocks.cleanstone.endpoint.cleanstone.net.packet.inbound.KeepAlive;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;

public enum CleanstoneInboundPacketType implements PacketType {
    KEEP_ALIVE(KeepAlive.class);

    private final Class<? extends Packet> packetClass;

    CleanstoneInboundPacketType(Class<? extends Packet> packetClass) {
        this.packetClass = packetClass;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.INBOUND;
    }
}
