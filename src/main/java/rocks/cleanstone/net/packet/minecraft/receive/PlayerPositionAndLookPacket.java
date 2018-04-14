package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;

public class PlayerPositionAndLookPacket extends ReceivePacket {

    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;
    private final boolean onGround;

    public PlayerPositionAndLookPacket(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
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

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public PacketType getType() {
        return MinecraftReceivePacketType.PLAYER_POSITION_AND_LOOK;
    }
}
