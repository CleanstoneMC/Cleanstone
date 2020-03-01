package rocks.cleanstone.storage.engine.leveldb.world;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.game.block.state.property.PropertyRegistry;
import rocks.cleanstone.game.entity.EntitySerialization;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.storage.world.WorldDataSource;
import rocks.cleanstone.storage.world.WorldDataSourceCreationException;
import rocks.cleanstone.storage.world.WorldDataSourceFactory;

@Slf4j
@Component
public class LevelDBWorldDataSourceFactory implements WorldDataSourceFactory {

    private final EntitySerialization entitySerialization;
    private final MaterialRegistry materialRegistry;
    private final PropertyRegistry propertyRegistry;

    public LevelDBWorldDataSourceFactory(EntitySerialization entitySerialization,
                                         MaterialRegistry materialRegistry, PropertyRegistry propertyRegistry) {
        this.entitySerialization = entitySerialization;
        this.materialRegistry = materialRegistry;
        this.propertyRegistry = propertyRegistry;
    }

    @Override
    public String getName() {
        return "leveldb";
    }

    @Override
    @SuppressWarnings("deprecation")
    public WorldDataSource get(String worldID) throws WorldDataSourceCreationException {
        try {
            return new LevelDBWorldDataSource(materialRegistry, propertyRegistry, entitySerialization,
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
