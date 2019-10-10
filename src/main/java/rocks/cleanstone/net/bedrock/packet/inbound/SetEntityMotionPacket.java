package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.utils.Vector;

public class SetEntityMotionPacket implements Packet {

    private final long runtimeEntityID;
    private final Vector velocity;

    public SetEntityMotionPacket(long runtimeEntityID, Vector velocity) {
        this.runtimeEntityID = runtimeEntityID;
        this.velocity = velocity;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public Vector getVelocity() {
        return velocity;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.SET_ENTITY_MOTION;
    }
}

