package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

import java.util.Collection;

public class DestroyEntitiesPacket implements Packet {

    private final Collection<Integer> entityIDs;

    public DestroyEntitiesPacket(Collection<Integer> entityIDs) {
        this.entityIDs = entityIDs;
    }

    public Collection<Integer> getEntityIDs() {
        return entityIDs;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.DESTROY_ENTITIES;
    }
}
