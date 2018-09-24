package rocks.cleanstone.game.world.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.entity.EntityTypeRegistry;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataCodecFactory;
import rocks.cleanstone.net.minecraft.protocol.v1_13.ProtocolBlockStateMapping;

@Component
@ConditionalOnProperty(name = "world.datasource", havingValue = "leveldb", matchIfMissing = true)
public class LevelDBWorldDataSourceFactory implements WorldDataSourceFactory {
    private final Logger logger = LoggerFactory.getLogger(LevelDBWorldDataSourceFactory.class);
    private final ProtocolBlockStateMapping blockStateMapping;
    private final VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory;
    private final EntityTypeRegistry entityTypeRegistry;

    public LevelDBWorldDataSourceFactory(
            ProtocolBlockStateMapping blockStateMapping,
            VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory,
            EntityTypeRegistry entityTypeRegistry
    ) {
        this.blockStateMapping = blockStateMapping;
        this.vanillaBlockDataCodecFactory = vanillaBlockDataCodecFactory;
        this.entityTypeRegistry = entityTypeRegistry;
    }

    @Override
    public WorldDataSource get(String worldID) throws WorldDataSourceCreationException {
        try {
            return new LevelDBWorldDataSource(vanillaBlockDataCodecFactory, entityTypeRegistry, blockStateMapping,
                    getDataFolder(), worldID);
        } catch (IOException e) {
            throw new WorldDataSourceCreationException("could not initialize leveldb", e);
        }
    }

    private Path getDataFolder() throws WorldDataSourceCreationException {
        Path directory = Paths.get("data");
        try {
            return Files.createDirectories(directory);
        } catch (IOException e) {
            throw new WorldDataSourceCreationException("could not create directory " + directory.toAbsolutePath() + " (no permissions?)", e);
        }
    }
}
