package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound;

import com.github.steveice10.opennbt.tag.builtin.Tag;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.storage.chunk.BlockDataStorage;

public class ChunkDataPacket implements Packet {

    private final int chunkX;
    private final int chunkZ;
    private final boolean groundUpContinuous;
    private final BlockDataStorage blockDataStorage;
    private final Tag blockEntities;

    public ChunkDataPacket(int chunkX, int chunkZ, boolean groundUpContinuous,
                           BlockDataStorage blockDataStorage, Tag blockEntities) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.groundUpContinuous = groundUpContinuous;
        this.blockDataStorage = blockDataStorage;
        this.blockEntities = blockEntities;
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkZ() {
        return chunkZ;
    }

    public boolean isGroundUpContinuous() {
        return groundUpContinuous;
    }

    public Tag getBlockEntities() {
        return blockEntities;
    }

    public BlockDataStorage getBlockDataStorage() {
        return blockDataStorage;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.CHUNK_DATA;
    }
}
