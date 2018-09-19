package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class TextPacket implements Packet {

    private final byte packetType;

    public TextPacket(byte packetType) {
        this.packetType =  packetType;
    }

    public byte getPacketType() {
        return packetType;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.TEXT;
    }
}

