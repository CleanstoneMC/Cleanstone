package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.bedrock.packet.data.PlayerAttributes;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UpdateAttributesPacket implements Packet {

    private final long runtimeEntityID;
    private final PlayerAttributes attributes;

    public UpdateAttributesPacket(long runtimeEntityID, PlayerAttributes attributes) {
        this.runtimeEntityID = runtimeEntityID;
        this.attributes = attributes;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public PlayerAttributes getAttributes() {
        return attributes;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.UPDATE_ATTRIBUTES;
    }
}

