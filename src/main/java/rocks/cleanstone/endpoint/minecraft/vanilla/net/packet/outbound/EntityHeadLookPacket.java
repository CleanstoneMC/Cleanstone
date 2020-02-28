package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EntityHeadLookPacket implements Packet {

    private final int entityID;
    private final float yaw;

    public EntityHeadLookPacket(int entityID, float yaw) {
        this.entityID = entityID;
        this.yaw = yaw;
    }

    public int getEntityID() {
        return entityID;
    }

    public float getYaw() {
        return yaw;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.ENTITY_HEAD_LOOK;
    }
}
