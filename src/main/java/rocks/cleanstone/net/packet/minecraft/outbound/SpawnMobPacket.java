package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.minecraft.data.EntityMetadata;
import rocks.cleanstone.net.packet.minecraft.enums.MobType;

import java.util.UUID;

public class SpawnMobPacket extends OutboundPacket {

    private final int entityID;
    private final UUID entityUUID;
    private final MobType mobType;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;
    private final float headPitch;
    private final short velocityX;
    private final short velocityY;
    private final short velocityZ;
    private final EntityMetadata entityMetadata;

    public SpawnMobPacket(int entityID, UUID entityUUID, int mobType, double x, double y, double z, float yaw, float pitch, float headPitch, short velocityX, short velocityY, short velocityZ, EntityMetadata entityMetadata) {
        this.entityID = entityID;
        this.entityUUID = entityUUID;
        this.mobType = MobType.fromTypeID(mobType);
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.headPitch = headPitch;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.entityMetadata = entityMetadata;
    }

    public SpawnMobPacket(int entityID, UUID entityUUID, MobType mobType, double x, double y, double z, float yaw, float pitch, float headPitch, short velocityX, short velocityY, short velocityZ, EntityMetadata entityMetadata) {
        this.entityID = entityID;
        this.entityUUID = entityUUID;
        this.mobType = mobType;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.headPitch = headPitch;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.entityMetadata = entityMetadata;
    }

    public int getEntityID() {
        return entityID;
    }

    public UUID getEntityUUID() {
        return entityUUID;
    }

    public MobType getMobType() {
        return mobType;
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

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public float getHeadPitch() {
        return headPitch;
    }

    public short getVelocityX() {
        return velocityX;
    }

    public short getVelocityY() {
        return velocityY;
    }

    public short getVelocityZ() {
        return velocityZ;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.SPAWN_MOB;
    }
}
