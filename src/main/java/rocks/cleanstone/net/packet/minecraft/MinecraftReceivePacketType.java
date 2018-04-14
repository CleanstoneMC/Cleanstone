package rocks.cleanstone.net.packet.minecraft;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.receive.HandshakePacket;
import rocks.cleanstone.net.packet.protocol.ProtocolType;
import rocks.cleanstone.net.packet.protocol.StandardProtocolType;

public enum MinecraftReceivePacketType implements PacketType {
    HANDSHAKE(0, HandshakePacket.class, StandardProtocolType.MINECRAFT_LATEST);

    private final int typeId;
    private final ProtocolType protocolType;
    private final Class<? extends Packet> packetClass;

    MinecraftReceivePacketType(int typeId, Class<? extends Packet> packetClass, ProtocolType protocolType) {
        this.typeId = typeId;
        this.packetClass = packetClass;
        this.protocolType = protocolType;
    }

    @Override
    public int getTypeId() {
        return typeId;
    }

    @Override
    public ProtocolType getProtocolType() {
        return protocolType;
    }
}
