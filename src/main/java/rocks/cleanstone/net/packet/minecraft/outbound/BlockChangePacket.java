package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.game.world.region.Position;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

public class BlockChangePacket {

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
