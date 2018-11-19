package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
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
        return MCPEOutboundPacketType.SIMPLE_EVENT;
    }
}

