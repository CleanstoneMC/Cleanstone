package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EntityFallPacket implements Packet {

    private final long runtimeEntityID;
    private final float fallDistance;
    private final boolean isInVoid;

    public EntityFallPacket(long runtimeEntityID, float fallDistance, boolean isInVoid) {
        this.runtimeEntityID = runtimeEntityID;
        this.fallDistance = fallDistance;
        this.isInVoid = isInVoid;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public float getFallDistance() {
        return fallDistance;
    }

    public boolean getIsInVoid() {
        return isInVoid;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.ENTITY_FALL;
    }
}

