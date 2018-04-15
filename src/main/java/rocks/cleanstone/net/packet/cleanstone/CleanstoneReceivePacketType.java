package rocks.cleanstone.net.packet.cleanstone;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.cleanstone.receive.KeepAlive;
import rocks.cleanstone.net.packet.protocol.ProtocolType;
import rocks.cleanstone.net.packet.protocol.StandardProtocolType;

public enum CleanstoneReceivePacketType implements PacketType {
    KEEP_ALIVE(0, KeepAlive.class, StandardProtocolType.CLEANSTONE);

    private final int typeId;
    private final ProtocolType protocolType;
    private final Class<? extends Packet> packetClass;

    CleanstoneReceivePacketType(int typeId, Class<? extends Packet> packetClass, ProtocolType protocolType) {
        this.typeId = typeId;
        this.packetClass = packetClass;
        this.protocolType = protocolType;
    }

    @Override
    public int getTypeId() {
        return 1000+typeId;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    @Override
    public ProtocolType getProtocolType() {
        return protocolType;
    }
}
