package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.PlayerAttributes;
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
        return MCPEOutboundPacketType.UPDATE_ATTRIBUTES;
    }
}

