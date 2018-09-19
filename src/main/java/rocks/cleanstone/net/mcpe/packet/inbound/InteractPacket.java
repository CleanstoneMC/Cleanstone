package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InteractPacket implements Packet {

    private final byte actionID;
    private final long targetRuntimeEntityID;

    public InteractPacket(byte actionID, long targetRuntimeEntityID) {
        this.actionID =  actionID;
        this.targetRuntimeEntityID =  targetRuntimeEntityID;
    }

    public byte getActionID() {
        return actionID;
    }

    public long getTargetRuntimeEntityID() {
        return targetRuntimeEntityID;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.INTERACT;
    }
}

