package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class MoveEntityDeltaPacket implements Packet {

    private final long runtimeEntityID;
    private final byte flags;

    public MoveEntityDeltaPacket(long runtimeEntityID, byte flags) {
        this.runtimeEntityID = runtimeEntityID;
        this.flags = flags;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public byte getFlags() {
        return flags;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.MOVE_ENTITY_DELTA;
    }
}

