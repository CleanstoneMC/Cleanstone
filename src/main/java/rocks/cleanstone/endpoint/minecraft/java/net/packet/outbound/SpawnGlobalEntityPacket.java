package rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.java.net.entity.VanillaGlobalEntityType;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SpawnGlobalEntityPacket implements Packet {

    private final int entityID;
    private final VanillaGlobalEntityType entityType;
    private final double x;
    private final double y;
    private final double z;

    public SpawnGlobalEntityPacket(int entityID, byte entityType, double x, double y, double z) {
        this.entityID = entityID;
        this.entityType = VanillaGlobalEntityType.fromTypeID(entityType);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SpawnGlobalEntityPacket(int entityID, VanillaGlobalEntityType entityType, double x, double y, double z) {
        this.entityID = entityID;
        this.entityType = entityType;
        this.x = x;
        this.y = y;
        this.z = z;
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

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.SPAWN_GLOBAL_ENTITY;
    }
}
