package rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound;

import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.storage.chunk.BlockDataStorage;

public class ChunkDataPacket implements Packet {

    private final int chunkX;
    private final int chunkZ;
    private final boolean groundUpContinuous;
    private final BlockDataStorage blockDataStorage;
    private final NamedBinaryTag[] blockEntities;

    public ChunkDataPacket(int chunkX, int chunkZ, boolean groundUpContinuous,
                           BlockDataStorage blockDataStorage, NamedBinaryTag[] blockEntities) {
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

    public NamedBinaryTag[] getBlockEntities() {
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
