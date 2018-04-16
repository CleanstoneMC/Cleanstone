package rocks.cleanstone.net.packet.cleanstone;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.cleanstone.inbound.KeepAlive;
import rocks.cleanstone.net.packet.protocol.ProtocolType;
import rocks.cleanstone.net.packet.protocol.StandardProtocolType;

public enum CleanstoneInboundPacketType implements PacketType {
    KEEP_ALIVE(0, KeepAlive.class);

    private final int typeId;
    private final Class<? extends Packet> packetClass;

    CleanstoneInboundPacketType(int typeId, Class<? extends Packet> packetClass) {
        this.typeId = typeId;
        this.packetClass = packetClass;
    }

    @Override
    public int getTypeId() {
        return 1000 + typeId;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    @Override
    public ProtocolType getProtocolType() {
        return StandardProtocolType.CLEANSTONE;
    }
}
