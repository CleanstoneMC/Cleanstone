package rocks.cleanstone.net.packet.outbound;

import java.util.UUID;

import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.data.EntityMetadata;

public class SpawnPlayerPacket implements Packet {

    private final int entityID;
    private final UUID playerUUID;
    private final RotatablePosition position;
    private final EntityMetadata entityMetadata;

    public SpawnPlayerPacket(int entityID, UUID playerUUID, RotatablePosition position, EntityMetadata
            entityMetadata) {
        this.entityID = entityID;
        this.playerUUID = playerUUID;
        this.position = position;
        this.entityMetadata = entityMetadata;
    }

    public int getEntityID() {
        return entityID;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public RotatablePosition getPosition() {
        return position;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.SPAWN_PLAYER;
    }
}
