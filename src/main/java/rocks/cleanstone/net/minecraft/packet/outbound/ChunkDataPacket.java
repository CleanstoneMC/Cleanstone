package rocks.cleanstone.net.minecraft.packet.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
import rocks.cleanstone.io.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ChunkDataPacket implements Packet, AutoCloseable {

    private final int chunkX;
    private final int chunkZ;
    private final boolean groundUpContinuous;
    private final int primaryBitMask;
    private final ByteBuf data;
    private final NamedBinaryTag[] blockEntities;

    public ChunkDataPacket(int chunkX, int chunkZ, boolean groundUpContinuous, int primaryBitMask,
                           ByteBuf data, NamedBinaryTag[] blockEntities) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.groundUpContinuous = groundUpContinuous;
        this.primaryBitMask = primaryBitMask;
        this.data = data;
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

    public int getPrimaryBitMask() {
        return primaryBitMask;
    }

    public ByteBuf getData() {
        return data;
    }

    public NamedBinaryTag[] getBlockEntities() {
        return blockEntities;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.CHUNK_DATA;
    }

    @Override
    public void close() {
        ReferenceCountUtil.release(data);
    }
}
