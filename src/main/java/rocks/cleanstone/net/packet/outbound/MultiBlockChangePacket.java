package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.data.BlockChange;

import java.util.List;

public class MultiBlockChangePacket implements Packet {

    private final int chunkX;
    private final int chunkZ;
    private final List<BlockChange> blockChangeList;

    public MultiBlockChangePacket(int chunkX, int chunkZ, List<BlockChange> blockChangeList) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.blockChangeList = blockChangeList;
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getchunkZ() {
        return chunkZ;
    }

    public List<BlockChange> getBlockChangeList() {
        return blockChangeList;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.MULTI_BLOCK_CHANGE;
    }
}
