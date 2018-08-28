package rocks.cleanstone.game.world.data;

import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataCodecFactory;
import rocks.cleanstone.net.minecraft.protocol.v1_13.ProtocolBlockStateMapping;

@Component
public class SimpleLevelDBWorldDataSourceFactory implements LevelDBWorldDataSourceFactory {

    private final ProtocolBlockStateMapping blockStateMapping;
    private final VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory;

    public SimpleLevelDBWorldDataSourceFactory(
            ProtocolBlockStateMapping blockStateMapping,
            VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory
    ) {
        this.blockStateMapping = blockStateMapping;
        this.vanillaBlockDataCodecFactory = vanillaBlockDataCodecFactory;
    }

    @Override
    public LevelDBWorldDataSource get(File worldDataFolder, String worldID) throws IOException {
        return new LevelDBWorldDataSource(vanillaBlockDataCodecFactory, blockStateMapping, worldDataFolder, worldID);
    }
}
