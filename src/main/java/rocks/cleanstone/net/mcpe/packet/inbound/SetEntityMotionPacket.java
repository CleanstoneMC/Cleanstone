package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
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
        return MCPEInboundPacketType.SET_ENTITY_MOTION;
    }
}

