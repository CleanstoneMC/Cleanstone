package rocks.cleanstone.game.world.data;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.game.entity.EntitySerialization;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataCodecFactory;
import rocks.cleanstone.net.minecraft.protocol.v1_13.ProtocolBlockStateMapping;

@Slf4j
@Component
@ConditionalOnProperty(name = "world.datasource", havingValue = "leveldb", matchIfMissing = true)
public class LevelDBWorldDataSourceFactory implements WorldDataSourceFactory {
    private final ProtocolBlockStateMapping blockStateMapping;
    private final VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory;
    private final EntitySerialization entitySerialization;

    public LevelDBWorldDataSourceFactory(
            ProtocolBlockStateMapping blockStateMapping,
            VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory,
            EntitySerialization entitySerialization
    ) {
        this.blockStateMapping = blockStateMapping;
        this.vanillaBlockDataCodecFactory = vanillaBlockDataCodecFactory;
        this.entitySerialization = entitySerialization;
    }

    @Override
    @SuppressWarnings("deprecation")
    public WorldDataSource get(String worldID) throws WorldDataSourceCreationException {
        try {
            return new LevelDBWorldDataSource(vanillaBlockDataCodecFactory, entitySerialization,
                    blockStateMapping, getDataFolder(), worldID);
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
