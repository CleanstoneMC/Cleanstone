package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PlayerPositionPacket implements Packet {

    private final double x;
    private final double feetY;
    private final double z;
    private final boolean onGround;

    public PlayerPositionPacket(double x, double feetY, double z, boolean onGround) {
        this.x = x;
        this.feetY = feetY;
        this.z = z;
        this.onGround = onGround;
    }

    public double getX() {
        return x;
    }

    public double getFeetY() {
        return feetY;
    }

    public double getZ() {
        return z;
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.PLAYER_POSITION;
    }
}
