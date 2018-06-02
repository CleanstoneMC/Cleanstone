package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EntityHeadLookPacket implements Packet {

    private final int entityID;
    private final int yaw;

    public EntityHeadLookPacket(int entityID, int yaw) {
        this.entityID = entityID;
        this.yaw = yaw;
    }

    public int getEntityID() {
        return entityID;
    }

    public int getYaw() {
        return yaw;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.ENTITY_HEAD_LOOK;
    }
}
