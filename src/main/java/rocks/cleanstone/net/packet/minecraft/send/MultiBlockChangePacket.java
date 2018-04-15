package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;
import rocks.cleanstone.net.packet.minecraft.data.BlockChange;

import java.util.List;

public class MultiBlockChangePacket extends SendPacket {

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
        return MinecraftSendPacketType.MULTI_BLOCK_CHANGE;
    }
}
