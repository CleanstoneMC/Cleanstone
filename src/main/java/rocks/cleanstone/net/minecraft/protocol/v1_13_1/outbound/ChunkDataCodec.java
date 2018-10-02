package rocks.cleanstone.net.minecraft.protocol.v1_13_1.outbound;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataCodecFactory;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorageFactory;

@Component
public class ChunkDataCodec extends rocks.cleanstone.net.minecraft.protocol.v1_13.outbound.ChunkDataCodec {
    public ChunkDataCodec(@Qualifier("protocolBlockStateMapping_v1_13_1") BlockStateMapping<Integer> blockStateMapping, VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory,
                          VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory) {
        super(blockStateMapping, vanillaBlockDataStorageFactory, vanillaBlockDataCodecFactory);

    }
}
