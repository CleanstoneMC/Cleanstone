package rocks.cleanstone.endpoint.minecraft.vanilla.v1_14.net.protocol.oldchunkdata;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.chunk.ChunkDataEncoder;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component("oldChunkDataEncoder_v1_14")
public class OldChunkDataEncoder implements ChunkDataEncoder {

    private final BlockStateMapping<Integer> blockStateMapping;
    private final VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory;
    private final VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory;

    public OldChunkDataEncoder(@Qualifier("protocolBlockStateMapping_v1_14") BlockStateMapping<Integer> blockStateMapping,
                               VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory,
                               VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory) {
        this.blockStateMapping = blockStateMapping;
        this.vanillaBlockDataStorageFactory = vanillaBlockDataStorageFactory;
        this.vanillaBlockDataCodecFactory = vanillaBlockDataCodecFactory;
    }

    @Override
    public ByteBuf encode(ChunkDataPacket packet, BlockStateMapping<Integer> blockStateMapping,
                          int bitsPerEntry) throws IOException {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(packet.getChunkX());
        byteBuf.writeInt(packet.getChunkZ());
        byteBuf.writeBoolean(packet.isGroundUpContinuous());

        DirectPalette directPalette = new DirectPalette(blockStateMapping, 14);
        VanillaBlockDataStorage storage = vanillaBlockDataStorageFactory.get(packet.getBlockDataStorage(),
                directPalette, true, true, true);

        ByteBuf blockData = vanillaBlockDataCodecFactory.get(directPalette, true, true, true, true,
                packet.getBlockDataStorage()).encode(storage);
        byteBuf.writeBytes(blockData);
        blockData.release();

        ByteBufUtils.writeVarInt(byteBuf, 0);
        //TODO encode NBT Tag array of block entities
        //ByteBufUtils.writeVarInt(byteBuf,chunkDataPacket.getBlockEntities().length);
        //byteBuf.writeBytes(chunkDataPacket.getBlockEntities())
        return byteBuf;
    }
}
