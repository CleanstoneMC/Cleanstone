package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class RemoveEntityPacket implements Packet {

    private final long entityIDSelf;

    public RemoveEntityPacket(long entityIDSelf) {
        this.entityIDSelf = entityIDSelf;
    }

    public long getEntityIDSelf() {
        return entityIDSelf;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.REMOVE_ENTITY;
    }
}

