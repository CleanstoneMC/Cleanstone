package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.MetadataDictionary;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetEntityDataPacket implements Packet {

    private final long runtimeEntityID;
    private final MetadataDictionary metadata;

    public SetEntityDataPacket(long runtimeEntityID, MetadataDictionary metadata) {
        this.runtimeEntityID =  runtimeEntityID;
        this.metadata =  metadata;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public MetadataDictionary getMetadata() {
        return metadata;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.SET_ENTITY_DATA;
    }
}

