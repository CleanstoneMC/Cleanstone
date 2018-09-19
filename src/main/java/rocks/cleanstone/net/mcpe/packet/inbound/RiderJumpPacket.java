package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class RiderJumpPacket implements Packet {

    private final int unknown;

    public RiderJumpPacket(int unknown) {
        this.unknown =  unknown;
    }

    public int getUnknown() {
        return unknown;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.RIDER_JUMP;
    }
}

