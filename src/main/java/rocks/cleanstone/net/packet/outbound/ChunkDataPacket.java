package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.game.world.chunk.data.block.BlockDataStorage;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ChunkDataPacket implements Packet {

    private final int chunkX;
    private final int chunkZ;
    private final boolean groundUpContinuous;
    private final BlockDataStorage storage;
    private final NamedBinaryTag[] blockEntities;

    public ChunkDataPacket(int chunkX, int chunkZ, boolean groundUpContinuous,
                           BlockDataStorage storage, NamedBinaryTag[] blockEntities) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.groundUpContinuous = groundUpContinuous;
        this.storage = storage;
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

    public BlockDataStorage getStorage() {
        return storage;
    }

    public NamedBinaryTag[] getBlockEntities() {
        return blockEntities;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.CHUNK_DATA;
    }
}
