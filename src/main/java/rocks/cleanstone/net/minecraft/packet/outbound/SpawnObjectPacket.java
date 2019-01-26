package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.minecraft.entity.VanillaObjectType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

import java.util.UUID;

public class SpawnObjectPacket implements Packet {

    private final int entityID;
    private final UUID objectUUID;
    private final VanillaObjectType entityType;
    private final double x;
    private final double y;
    private final double z;
    private final float pitch;
    private final float yaw;
    private final int data;
    private final short velocityX;
    private final short velocityY;
    private final short velocityZ;

    public SpawnObjectPacket(int entityID, UUID objectUUID, byte entityType, double x, double y, double z, float pitch, float yaw, int data, short velocityX, short velocityY, short velocityZ) {
        this.entityID = entityID;
        this.objectUUID = objectUUID;
        this.entityType = VanillaObjectType.fromTypeID(entityType);
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.data = data;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    public SpawnObjectPacket(int entityID, UUID objectUUID, VanillaObjectType entityType, double x, double y, double z, float pitch, float yaw, int data, short velocityX, short velocityY, short velocityZ) {
        this.entityID = entityID;
        this.objectUUID = objectUUID;
        this.entityType = entityType;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.data = data;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    public SpawnObjectPacket(int entityID, UUID objectUUID, VanillaObjectType entityType, RotatablePosition position, int data, short velocityX, short velocityY, short velocityZ) {
        this.entityID = entityID;
        this.objectUUID = objectUUID;
        this.entityType = entityType;
        this.x = position.getX();
        this.y = position.getY();
        this.z = position.getZ();
        this.pitch = position.getRotation().getPitch();
        this.yaw = position.getRotation().getYaw();
        this.data = data;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    public int getEntityID() {
        return entityID;
    }

    public UUID getObjectUUID() {
        return objectUUID;
    }

    public VanillaObjectType getEntityType() {
        return entityType;
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

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public int getData() {
        return data;
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

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.SPAWN_OBJECT;
    }
}
