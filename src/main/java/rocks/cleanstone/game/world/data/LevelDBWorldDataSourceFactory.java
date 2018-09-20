package rocks.cleanstone.game.world.data;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.entity.EntityTypeRegistry;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataCodecFactory;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorageFactory;
import rocks.cleanstone.net.minecraft.protocol.v1_13.ProtocolBlockStateMapping;

@Component
@ConditionalOnProperty(name = "world.datasource", havingValue = "leveldb", matchIfMissing = true)
public class LevelDBWorldDataSourceFactory implements WorldDataSourceFactory {
    private final Logger logger = LoggerFactory.getLogger(LevelDBWorldDataSourceFactory.class);
    private final ProtocolBlockStateMapping blockStateMapping;
    private final VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory;
    private final VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory;
    private final EntityTypeRegistry entityTypeRegistry;

    public LevelDBWorldDataSourceFactory(
            ProtocolBlockStateMapping blockStateMapping,
            VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory,
            VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory,
            EntityTypeRegistry entityTypeRegistry
    ) {
        this.blockStateMapping = blockStateMapping;
        this.vanillaBlockDataCodecFactory = vanillaBlockDataCodecFactory;
        this.vanillaBlockDataStorageFactory = vanillaBlockDataStorageFactory;
        this.entityTypeRegistry = entityTypeRegistry;
    }

    @Override
    public WorldDataSource get(String worldID) throws IOException {
        File dataFolder = new File("data");
        try {
            dataFolder.mkdir();
        } catch (SecurityException e) {
            logger.error("Cannot create data folder (no permission?)", e);
        }

        return new LevelDBWorldDataSource(vanillaBlockDataCodecFactory, vanillaBlockDataStorageFactory,
                entityTypeRegistry, blockStateMapping, dataFolder, worldID);
    }
}
