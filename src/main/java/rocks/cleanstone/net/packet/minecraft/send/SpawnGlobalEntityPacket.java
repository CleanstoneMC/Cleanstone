package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.GlobalEntityType;

public class SpawnGlobalEntityPacket extends SendPacket {

    private final int entityID;
    private final GlobalEntityType type;
    private final double x;
    private final double y;
    private final double z;

    public SpawnGlobalEntityPacket(int entityID, byte type, double x, double y, double z) {
        this.entityID = entityID;
        this.type = GlobalEntityType.fromTypeID(type);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SpawnGlobalEntityPacket(int entityID, GlobalEntityType type, double x, double y, double z) {
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
        return MinecraftSendPacketType.SPAWN_GLOBAL_ENTITY;
    }
}
