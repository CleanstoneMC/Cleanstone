package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.game.world.region.Position;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

public class BlockBreakAnimationPacket implements Packet {

    private final int entityID;
    private final Position location;
    private final byte destroyStage;

    public BlockBreakAnimationPacket(int entityID, Position location, byte destroyStage) {
        this.entityID = entityID;
        this.location = location;
        this.destroyStage = destroyStage;
    }

    public int getEntityID() {
        return entityID;
    }

    public Position getLocation() {
        return location;
    }

    public byte getDestroyStage() {
        return destroyStage;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.BLOCK_BREAK_ANIMATION;
    }
}
