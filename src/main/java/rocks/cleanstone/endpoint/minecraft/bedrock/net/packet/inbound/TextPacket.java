package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class TextPacket implements Packet {

    private final byte packetType;

    public TextPacket(byte packetType) {
        this.packetType = packetType;
    }

    public byte getPacketType() {
        return packetType;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.TEXT;
    }
}

