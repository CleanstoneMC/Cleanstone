package rocks.cleanstone.storage.engine.rocksdb.world;

import lombok.extern.slf4j.Slf4j;
import org.rocksdb.RocksDBException;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.property.PropertyRegistry;
import rocks.cleanstone.game.entity.EntitySerialization;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.storage.world.WorldDataSource;
import rocks.cleanstone.storage.world.WorldDataSourceCreationException;
import rocks.cleanstone.storage.world.WorldDataSourceFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class RocksDBWorldDataSourceFactory implements WorldDataSourceFactory {

    private final EntitySerialization entitySerialization;
    private final MaterialRegistry materialRegistry;
    private final PropertyRegistry propertyRegistry;

    public RocksDBWorldDataSourceFactory(EntitySerialization entitySerialization,
                                         MaterialRegistry materialRegistry, PropertyRegistry propertyRegistry) {
        this.entitySerialization = entitySerialization;
        this.materialRegistry = materialRegistry;
        this.propertyRegistry = propertyRegistry;
    }

    @Override
    public String getName() {
        return "rocksdb";
    }

    @Override
    @SuppressWarnings("deprecation")
    public WorldDataSource get(String worldID) throws WorldDataSourceCreationException {
        try {
            return new RocksDBWorldDataSource(materialRegistry, propertyRegistry, entitySerialization,
                    getDataFolder(), worldID);
        } catch (RocksDBException e) {
            throw new WorldDataSourceCreationException("could not initialize rocksdb", e);
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
