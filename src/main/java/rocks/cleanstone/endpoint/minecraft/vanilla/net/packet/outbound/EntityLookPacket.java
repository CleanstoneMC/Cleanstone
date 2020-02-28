package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EntityLookPacket implements Packet {

    private final int entityID;
    private final float yaw;
    private final float pitch;
    private final boolean onGround;

    public EntityLookPacket(int entityID, float yaw, float pitch, boolean onGround) {
        this.entityID = entityID;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    public int getEntityID() {
        return entityID;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.ENTITY_LOOK;
    }
}
