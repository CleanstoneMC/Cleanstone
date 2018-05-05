package rocks.cleanstone.net.cleanstone.packet;

import rocks.cleanstone.net.cleanstone.packet.outbound.KeepAlive;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;

public enum CleanstoneOutboundPacketType implements PacketType {
    KEEP_ALIVE(0, KeepAlive.class);

    private final int typeId;
    private final Class<? extends Packet> packetClass;

    CleanstoneOutboundPacketType(int typeId, Class<? extends Packet> packetClass) {
        this.typeId = typeId;
        this.packetClass = packetClass;
    }

    @Override
    public int getTypeID() {
        return 2000 + typeId;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.OUTBOUND;
    }
}