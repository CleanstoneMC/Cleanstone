package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;

public class SpawnExperienceOrbPacket extends SendPacket {

    private final int entityID;
    private final double x;
    private final double y;
    private final double z;
    private final short count;

    public SpawnExperienceOrbPacket(int entityID, double x, double y, double z, short count) {
        this.entityID = entityID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.count = count;
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

    public short getCount() {
        return count;
    }

    @Override
    public PacketType getType() {
        return MinecraftSendPacketType.SPAWN_EXPERIENCE_ORB;
    }
}
