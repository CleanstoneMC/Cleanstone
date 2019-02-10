package rocks.cleanstone.net.minecraft.packet.outbound;

import java.util.UUID;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.utils.Vector;

public class SpawnMobPacket implements Packet {

    private final int entityID;
    private final UUID entityUUID;
    private final HeadRotatablePosition position;
    private final Vector velocity;
    private final Entity entity;

    public SpawnMobPacket(int entityID, UUID entityUUID, HeadRotatablePosition position,
                          Vector velocity, Entity entity) {
        this.entityID = entityID;
        this.entityUUID = entityUUID;
        this.position = position;
        this.velocity = velocity;
        this.entity = entity;
    }

    public int getEntityID() {
        return entityID;
    }

    public UUID getEntityUUID() {
        return entityUUID;
    }

    public HeadRotatablePosition getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.SPAWN_MOB;
    }
}
