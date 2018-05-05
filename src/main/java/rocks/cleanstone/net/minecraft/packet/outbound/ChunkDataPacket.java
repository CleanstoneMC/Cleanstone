package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.io.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ChunkDataPacket implements Packet {

    private final int chunkX;
    private final int chunkZ;
    private final boolean groundUpContinuous;
    private final int primaryBitMask;
    private final byte[] data;
    private final NamedBinaryTag[] blockEntites;

    public ChunkDataPacket(int chunkX, int chunkZ, boolean groundUpContinuous, int primaryBitMask, byte[] data, NamedBinaryTag[] blockEntites) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.groundUpContinuous = groundUpContinuous;
        this.primaryBitMask = primaryBitMask;
        this.data = data;
        this.blockEntites = blockEntites;
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

    public int getPrimaryBitMask() {
        return primaryBitMask;
    }

    public byte[] getData() {
        return data;
    }

    public NamedBinaryTag[] getBlockEntites() {
        return blockEntites;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.CHUNK_DATA;
    }
}
