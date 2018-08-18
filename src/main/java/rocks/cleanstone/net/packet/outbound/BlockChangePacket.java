package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class BlockChangePacket implements Packet {

    private final Position position;
    private final Block block;

    public BlockChangePacket(Position position, Block block) {
        this.position = position;
        this.block = block;
    }

    public Position getPosition() {
        return position;
    }

    public Block getBlock() {
        return block;
    }

    public int getBlockData() {
        int blockID = block.getState().getBlockType().getID();
        int metaData = 0;//block.getState().getMetadata(); //TODO: Add Metadata

        return blockID << 4 | (metaData & 15);
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.BLOCK_CHANGE;
    }
}
