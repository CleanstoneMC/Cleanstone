package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.data.EntityMetadata;

public class EntityMetadataPacket implements Packet {

    private final int entityID;
    private final EntityMetadata entityMetadata;

    public EntityMetadataPacket(int entityID, EntityMetadata entityMetadata) {
        this.entityID = entityID;
        this.entityMetadata = entityMetadata;
    }


    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.ENTITY_METADATA;
    }
}
