package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class MovePlayerPacket implements Packet {

    private final long runtimeEntityID;
    private final float x;
    private final float y;
    private final float z;
    private final float pitch;
    private final float yaw;
    private final float headYaw;
    private final byte mode;
    private final boolean onGround;
    private final long otherRuntimeEntityID;

    public MovePlayerPacket(long runtimeEntityID, float x, float y, float z, float pitch, float yaw, float headYaw, byte mode, boolean onGround, long otherRuntimeEntityID) {
        this.runtimeEntityID = runtimeEntityID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
        this.mode = mode;
        this.onGround = onGround;
        this.otherRuntimeEntityID = otherRuntimeEntityID;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getHeadYaw() {
        return headYaw;
    }

    public byte getMode() {
        return mode;
    }

    public boolean getOnGround() {
        return onGround;
    }

    public long getOtherRuntimeEntityID() {
        return otherRuntimeEntityID;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.MOVE_PLAYER;
    }
}

