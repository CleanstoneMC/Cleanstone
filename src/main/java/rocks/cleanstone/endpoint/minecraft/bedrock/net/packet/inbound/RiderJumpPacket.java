package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class RiderJumpPacket implements Packet {

    private final int unknown;

    public RiderJumpPacket(int unknown) {
        this.unknown = unknown;
    }

    public int getUnknown() {
        return unknown;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.RIDER_JUMP;
    }
}

