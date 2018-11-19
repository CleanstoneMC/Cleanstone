package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
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
        return MCPEOutboundPacketType.SET_LAST_HURT_BY;
    }
}

