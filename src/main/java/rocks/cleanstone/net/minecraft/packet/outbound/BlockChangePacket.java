package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class BlockChangePacket implements Packet {

    private final Position location;
    private final Block block;

    public BlockChangePacket(Position location, Block block) {
        this.location = location;
        this.block = block;
    }

    public Position getLocation() {
        return location;
    }

    public Block getBlock() {
        return block;
    }

    public int getBlockData() {
        int blockID = block.getState().getMaterial().getID();
        int metaData = block.getState().getMetadata();

        return blockID << 4 | (metaData & 15);
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.BLOCK_CHANGE;
    }
}
