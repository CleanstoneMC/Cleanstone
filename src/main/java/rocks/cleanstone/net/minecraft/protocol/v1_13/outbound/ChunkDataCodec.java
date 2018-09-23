package rocks.cleanstone.net.minecraft.protocol.v1_13.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.DirectPalette;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataCodecFactory;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorage;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorageFactory;
import rocks.cleanstone.net.minecraft.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class ChunkDataCodec implements PacketCodec<ChunkDataPacket> {

    private final BlockStateMapping<Integer> blockStateMapping;
    private final VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory;
    private final VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory;

    public ChunkDataCodec(@Qualifier("protocolBlockStateMapping_v1_13") BlockStateMapping<Integer> blockStateMapping, VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory,
                          VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory) {
        this.blockStateMapping = blockStateMapping;
        this.vanillaBlockDataStorageFactory = vanillaBlockDataStorageFactory;
        this.vanillaBlockDataCodecFactory = vanillaBlockDataCodecFactory;
    }

    @Override
    public ChunkDataPacket decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("ChunkData is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, ChunkDataPacket packet) {
        byteBuf.writeInt(packet.getChunkX());
        byteBuf.writeInt(packet.getChunkZ());
        byteBuf.writeBoolean(packet.isGroundUpContinuous());

        DirectPalette directPalette = new DirectPalette(blockStateMapping, 14);
        VanillaBlockDataStorage storage = vanillaBlockDataStorageFactory.get(packet.getBlockDataTable(),
                directPalette, true);

        ByteBuf blockData = vanillaBlockDataCodecFactory.get(directPalette, true).serialize(storage);
        byteBuf.writeBytes(blockData);
        blockData.release();

        ByteBufUtils.writeVarInt(byteBuf, 0);
        //TODO encode NBT Tag array of block entities
        //ByteBufUtils.writeVarInt(byteBuf,chunkDataPacket.getBlockEntities().length);
        //byteBuf.writeBytes(chunkDataPacket.getBlockEntities())

        return byteBuf;
    }
}
