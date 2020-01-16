package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PlayerInputPacket implements Packet {

    private final float motionX;
    private final float motionZ;
    private final boolean jumping;
    private final boolean sneaking;

    public PlayerInputPacket(float motionX, float motionZ, boolean jumping, boolean sneaking) {
        this.motionX = motionX;
        this.motionZ = motionZ;
        this.jumping = jumping;
        this.sneaking = sneaking;
    }

    public float getMotionX() {
        return motionX;
    }

    public float getMotionZ() {
        return motionZ;
    }

    public boolean getJumping() {
        return jumping;
    }

    public boolean getSneaking() {
        return sneaking;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.PLAYER_INPUT;
    }
}

