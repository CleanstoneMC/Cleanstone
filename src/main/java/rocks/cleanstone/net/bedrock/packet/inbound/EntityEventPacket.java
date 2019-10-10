package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EntityEventPacket implements Packet {

    private final long runtimeEntityID;
    private final byte eventID;
    private final int data;

    public EntityEventPacket(long runtimeEntityID, byte eventID, int data) {
        this.runtimeEntityID = runtimeEntityID;
        this.eventID = eventID;
        this.data = data;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public byte getEventID() {
        return eventID;
    }

    public int getData() {
        return data;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.ENTITY_EVENT;
    }
}

