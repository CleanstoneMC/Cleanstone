package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InteractPacket implements Packet {

    private final byte actionID;
    private final long targetRuntimeEntityID;

    public InteractPacket(byte actionID, long targetRuntimeEntityID) {
        this.actionID = actionID;
        this.targetRuntimeEntityID = targetRuntimeEntityID;
    }

    public byte getActionID() {
        return actionID;
    }

    public long getTargetRuntimeEntityID() {
        return targetRuntimeEntityID;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.INTERACT;
    }
}

