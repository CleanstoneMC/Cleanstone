package rocks.cleanstone.net.minecraft.packet.outbound;

import java.util.UUID;

import rocks.cleanstone.net.minecraft.entity.VanillaEntity;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SpawnMobPacket implements Packet {

    private final int entityID;
    private final UUID entityUUID;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;
    private final float headPitch;
    private final short velocityX;
    private final short velocityY;
    private final short velocityZ;
    private final VanillaEntity vanillaEntity;

    public SpawnMobPacket(int entityID, UUID entityUUID, double x, double y, double z, float yaw,
                          float pitch, float headPitch, short velocityX, short velocityY, short velocityZ,
                          VanillaEntity vanillaEntity) {
        this.entityID = entityID;
        this.entityUUID = entityUUID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.headPitch = headPitch;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.vanillaEntity = vanillaEntity;
    }

    public int getEntityID() {
        return entityID;
    }

    public UUID getEntityUUID() {
        return entityUUID;
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

    public VanillaEntity getVanillaEntity() {
        return vanillaEntity;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.SPAWN_MOB;
    }
}
