package rocks.cleanstone.net.packet.outbound;

import java.util.List;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.data.BlockChange;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class MultiBlockChangePacket implements Packet {

    private final int chunkX;
    private final int chunkY;
    private final List<BlockChange> blockChangeList;

    public MultiBlockChangePacket(int chunkX, int chunkY, List<BlockChange> blockChangeList) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
        this.blockChangeList = blockChangeList;
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkY() {
        return chunkY;
    }

    public List<BlockChange> getBlockChangeList() {
        return blockChangeList;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.MULTI_BLOCK_CHANGE;
    }
}
