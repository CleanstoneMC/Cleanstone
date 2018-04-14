package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;

public class PlayerPositionPacket extends ReceivePacket {

    private final double x;
    private final double y;
    private final double z;
    private final boolean onGround;

    public PlayerPositionPacket(double x, double y, double z, boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public PacketType getType() {
        return MinecraftReceivePacketType.PLAYER_POSITION;
    }
}
