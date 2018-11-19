package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class RemoveEntityPacket implements Packet {

    private final long entityIDSelf;

    public RemoveEntityPacket(long entityIDSelf) {
        this.entityIDSelf = entityIDSelf;
    }

    public long getEntityIDSelf() {
        return entityIDSelf;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.REMOVE_ENTITY;
    }
}

