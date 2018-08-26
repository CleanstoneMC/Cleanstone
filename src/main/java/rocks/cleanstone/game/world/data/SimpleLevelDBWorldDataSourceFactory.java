package rocks.cleanstone.game.world.data;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.BlockStateProvider;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataCodecFactory;

import java.io.File;
import java.io.IOException;

@Component
public class SimpleLevelDBWorldDataSourceFactory implements LevelDBWorldDataSourceFactory {

    private final BlockStateProvider blockStateProvider;
    private final VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory;

    public SimpleLevelDBWorldDataSourceFactory(BlockStateProvider blockStateProvider,
                                               VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory) {
        this.blockStateProvider = blockStateProvider;
        this.vanillaBlockDataCodecFactory = vanillaBlockDataCodecFactory;
    }

    @Override
    public LevelDBWorldDataSource get(File worldDataFolder, String worldID) throws IOException {
        return new LevelDBWorldDataSource(blockStateProvider, vanillaBlockDataCodecFactory, worldDataFolder, worldID);
    }
}
