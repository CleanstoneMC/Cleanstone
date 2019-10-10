package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.bedrock.packet.data.MetadataDictionary;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetEntityDataPacket implements Packet {

    private final long runtimeEntityID;
    private final MetadataDictionary metadata;

    public SetEntityDataPacket(long runtimeEntityID, MetadataDictionary metadata) {
        this.runtimeEntityID = runtimeEntityID;
        this.metadata = metadata;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public MetadataDictionary getMetadata() {
        return metadata;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.SET_ENTITY_DATA;
    }
}

