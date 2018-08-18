package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.world.chunk.data.block.BlockDataCodec;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class ChunkDataCodec implements PacketCodec {

    private final BlockStateMapping<Integer> blockStateMapping;

    public ChunkDataCodec(BlockStateMapping<Integer> blockStateMapping) {
        this.blockStateMapping = blockStateMapping;
    }

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("ChunkData is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        ChunkDataPacket chunkDataPacket = (ChunkDataPacket) packet;
        byteBuf.writeInt(chunkDataPacket.getChunkX());
        byteBuf.writeInt(chunkDataPacket.getChunkZ());
        byteBuf.writeBoolean(chunkDataPacket.isGroundUpContinuous());

        ByteBuf blockData = new BlockDataCodec(blockStateMapping).serialize(chunkDataPacket.getStorage());
        byteBuf.writeBytes(blockData);
        blockData.release();

        ByteBufUtils.writeVarInt(byteBuf, 0);
        //TODO encode NBT Tag array of block entities
        //ByteBufUtils.writeVarInt(byteBuf,chunkDataPacket.getBlockEntities().length);
        //byteBuf.writeBytes(chunkDataPacket.getBlockEntities())

        return byteBuf;
    }

    @Override
    public ByteBuf upgradeByteBuf(ByteBuf previousLayerByteBuf) {
        return previousLayerByteBuf;
    }

    @Override
    public ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf) {
        return nextLayerByteBuf;
    }
}
