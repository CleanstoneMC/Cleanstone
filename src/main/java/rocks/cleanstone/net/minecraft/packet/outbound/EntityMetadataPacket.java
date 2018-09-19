package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.minecraft.packet.data.EntityMetadata;
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
