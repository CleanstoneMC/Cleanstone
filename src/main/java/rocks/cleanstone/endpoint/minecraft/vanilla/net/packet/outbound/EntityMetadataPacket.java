package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EntityMetadataPacket implements Packet {

    private final int entityID;
    private final Entity entity;

    public EntityMetadataPacket(int entityID, Entity entity) {
        this.entityID = entityID;
        this.entity = entity;
    }

    public int getEntityID() {
        return entityID;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.ENTITY_METADATA;
    }
}
