package rocks.cleanstone.net.cleanstone.packet;

import rocks.cleanstone.net.cleanstone.packet.outbound.KeepAlive;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;

public enum CleanstoneOutboundPacketType implements PacketType {
    KEEP_ALIVE(KeepAlive.class);

    private final Class<? extends Packet> packetClass;

    CleanstoneOutboundPacketType(Class<? extends Packet> packetClass) {
        this.packetClass = packetClass;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.OUTBOUND;
    }
}