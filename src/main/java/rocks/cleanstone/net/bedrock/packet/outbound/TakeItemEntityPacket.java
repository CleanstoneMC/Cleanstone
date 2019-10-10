package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class TakeItemEntityPacket implements Packet {

    private final long runtimeEntityID;
    private final long target;

    public TakeItemEntityPacket(long runtimeEntityID, long target) {
        this.runtimeEntityID = runtimeEntityID;
        this.target = target;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public long getTarget() {
        return target;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.TAKE_ITEM_ENTITY;
    }
}

