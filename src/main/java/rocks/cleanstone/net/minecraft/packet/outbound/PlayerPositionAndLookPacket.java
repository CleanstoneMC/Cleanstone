package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PlayerPositionAndLookPacket implements Packet {

    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;
    private final int flags;
    private final int teleportID;

    public PlayerPositionAndLookPacket(double x, double y, double z, float yaw, float pitch, int flags, int teleportID) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.flags = flags;
        this.teleportID = teleportID;
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

    public int getFlags() {
        return flags;
    }

    public int getTeleportID() {
        return teleportID;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.PLAYER_POSITION_AND_LOOK;
    }
}
