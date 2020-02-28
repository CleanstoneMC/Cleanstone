package rocks.cleanstone.storage.engine.leveldb.world;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJndi;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.chunk.ChunkDataEncoder;
import rocks.cleanstone.game.entity.EntitySerialization;
import rocks.cleanstone.storage.world.WorldDataSource;
import rocks.cleanstone.storage.world.WorldDataSourceCreationException;
import rocks.cleanstone.storage.world.WorldDataSourceFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
@ConditionalOnJndi("leveldbjni")
public class LevelDBWorldDataSourceFactory implements WorldDataSourceFactory {

    private final EntitySerialization entitySerialization;
    private final ChunkDataEncoder chunkDataEncoder;

    public LevelDBWorldDataSourceFactory(
            EntitySerialization entitySerialization,
            @Qualifier("chunkDataEncoder_v1_14") ChunkDataEncoder chunkDataEncoder
    ) {
        this.entitySerialization = entitySerialization;
        this.chunkDataEncoder = chunkDataEncoder;
    }

    @Override
    public String getName() {
        return "leveldb";
    }

    @Override
    @SuppressWarnings("deprecation")
    public WorldDataSource get(String worldID) throws WorldDataSourceCreationException {
        try {
            return new LevelDBWorldDataSource(entitySerialization, chunkDataEncoder, getDataFolder(), worldID);
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
