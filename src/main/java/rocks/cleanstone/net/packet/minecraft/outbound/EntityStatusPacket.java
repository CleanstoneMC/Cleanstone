package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.EntityStatus;

public class EntityStatusPacket implements Packet {

    private final int entityID;
    private final EntityStatus entityStatus;

    public EntityStatusPacket(int entityID, byte entityStatus) {
        this.entityID = entityID;
        this.entityStatus = EntityStatus.fromStatusID(entityID);
    }

    public EntityStatusPacket(int entityID, EntityStatus entityStatus) {
        this.entityID = entityID;
        this.entityStatus = entityStatus;
    }

    public int getEntityID() {
        return entityID;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.ENTITY_STATUS;
    }
}
