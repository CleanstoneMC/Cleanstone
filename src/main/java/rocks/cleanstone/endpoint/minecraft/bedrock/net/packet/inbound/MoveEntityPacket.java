package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class MoveEntityPacket implements Packet {

    private final long runtimeEntityID;
    private final byte flags;
    private final HeadRotatablePosition position;

    public MoveEntityPacket(long runtimeEntityID, byte flags, HeadRotatablePosition position) {
        this.runtimeEntityID = runtimeEntityID;
        this.flags = flags;
        this.position = position;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public byte getFlags() {
        return flags;
    }

    public HeadRotatablePosition getPosition() {
        return position;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.MOVE_ENTITY;
    }
}

