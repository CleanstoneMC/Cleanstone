package rocks.cleanstone.net.cleanstone.packet;

import rocks.cleanstone.net.cleanstone.packet.inbound.KeepAlive;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;

public enum CleanstoneInboundPacketType implements PacketType {
    KEEP_ALIVE(0, KeepAlive.class);

    private final int typeId;
    private final Class<? extends Packet> packetClass;

    CleanstoneInboundPacketType(int typeId, Class<? extends Packet> packetClass) {
        this.typeId = typeId;
        this.packetClass = packetClass;
    }

    @Override
    public int getTypeID() {
        return 1000 + typeId;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.INBOUND;
    }
}
