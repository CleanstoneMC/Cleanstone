package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SimpleEventPacket implements Packet {

    private final short eventType;

    public SimpleEventPacket(short eventType) {
        this.eventType = eventType;
    }

    public short getEventType() {
        return eventType;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SIMPLE_EVENT;
    }
}

