package rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.java.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.data.BlockChange;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

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
