package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.game.world.region.Position;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class BlockChangePacket implements Packet {

    private final Position location;
    private final int blockID;

    public BlockChangePacket(Position location, int blockID) {
        this.location = location;
        this.blockID = blockID;
    }

    public Position getLocation() {
        return location;
    }

    public int getBlockID() {
        return blockID;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.BLOCK_CHANGE;
    }
}
