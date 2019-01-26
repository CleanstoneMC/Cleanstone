package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.minecraft.entity.VanillaGlobalEntityType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SpawnGlobalEntityPacket implements Packet {

    private final int entityID;
    private final VanillaGlobalEntityType type;
    private final double x;
    private final double y;
    private final double z;

    public SpawnGlobalEntityPacket(int entityID, byte type, double x, double y, double z) {
        this.entityID = entityID;
        this.type = VanillaGlobalEntityType.fromTypeID(type);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SpawnGlobalEntityPacket(int entityID, VanillaGlobalEntityType type, double x, double y, double z) {
        this.entityID = entityID;
        this.type = type;
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
