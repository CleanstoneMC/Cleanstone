package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.EntityMetadata;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

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
