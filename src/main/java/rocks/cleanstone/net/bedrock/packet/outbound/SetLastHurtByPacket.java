package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetLastHurtByPacket implements Packet {

    private final int unknown;

    public SetLastHurtByPacket(int unknown) {
        this.unknown = unknown;
    }

    public int getUnknown() {
        return unknown;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SET_LAST_HURT_BY;
    }
}

