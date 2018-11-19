package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class AnimatePacket implements Packet {

    private final int actionID;
    private final long runtimeEntityID;

    public AnimatePacket(int actionID, long runtimeEntityID) {
        this.actionID = actionID;
        this.runtimeEntityID = runtimeEntityID;
    }

    public int getActionID() {
        return actionID;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.ANIMATE;
    }
}

