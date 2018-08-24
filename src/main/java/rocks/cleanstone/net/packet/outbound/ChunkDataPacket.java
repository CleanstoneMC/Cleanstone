package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ChunkDataPacket implements Packet {

    private final int chunkX;
    private final int chunkZ;
    private final boolean groundUpContinuous;
    private final BlockDataTable blockDataTable;
    private final NamedBinaryTag[] blockEntities;

    public ChunkDataPacket(int chunkX, int chunkZ, boolean groundUpContinuous,
                           BlockDataTable blockDataTable, NamedBinaryTag[] blockEntities) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.groundUpContinuous = groundUpContinuous;
        this.blockDataTable = blockDataTable;
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

    public BlockDataTable getBlockDataTable() {
        return blockDataTable;
    }

    public NamedBinaryTag[] getBlockEntities() {
        return blockEntities;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.CHUNK_DATA;
    }
}
