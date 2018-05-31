package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EntityTeleportPacket implements Packet {

    private final int entityID;
    private final double x;
    private final double y;
    private final double z;
    private final int yaw;
    private final int pitch;
    private final boolean onGround;

    public EntityTeleportPacket(int entityID, double x, double y, double z, int yaw, int pitch, boolean onGround) {
        this.entityID = entityID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    public int getEntityID() {
        return entityID;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public int getYaw() {
        return yaw;
    }

    public int getPitch() {
        return pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.ENTITY_TELEPORT;
    }
}
