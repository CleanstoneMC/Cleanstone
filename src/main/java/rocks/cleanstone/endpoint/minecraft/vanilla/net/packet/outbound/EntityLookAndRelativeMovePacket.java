package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EntityLookAndRelativeMovePacket implements Packet {

    private final int entityID;
    private final short deltaX;
    private final short deltaY;
    private final short deltaZ;
    private final float yaw;
    private final float pitch;
    private final boolean onGround;

    public EntityLookAndRelativeMovePacket(int entityID, short deltaX, short deltaY, short deltaZ, float yaw, float pitch, boolean onGround) {
        this.entityID = entityID;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    public int getEntityID() {
        return entityID;
    }

    public short getDeltaX() {
        return deltaX;
    }

    public short getDeltaY() {
        return deltaY;
    }

    public short getDeltaZ() {
        return deltaZ;
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
        return MinecraftOutboundPacketType.ENTITY_LOOK_AND_RELATIVE_MOVE;
    }
}
