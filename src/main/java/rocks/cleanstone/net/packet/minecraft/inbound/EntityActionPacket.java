package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.InboundPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.EntityAction;

public class EntityActionPacket extends InboundPacket {

    private final int entityID;
    private final EntityAction entityAction;
    private final int jumpBoost;

    public EntityActionPacket(int entityID, int entityAction, int jumpBoost) {
        this.entityID = entityID;
        this.entityAction = EntityAction.fromActionID(entityAction);
        this.jumpBoost = jumpBoost;
    }

    public EntityActionPacket(int entityID, EntityAction entityAction, int jumpBoost) {
        this.entityID = entityID;
        this.entityAction = entityAction;
        this.jumpBoost = jumpBoost;
    }

    public int getEntityID() {
        return entityID;
    }

    public EntityAction getEntityAction() {
        return entityAction;
    }

    public int getJumpBoost() {
        return jumpBoost;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.ENTITY_ACTION;
    }
}
