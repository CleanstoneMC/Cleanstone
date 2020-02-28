package rocks.cleanstone.game.world;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.config.structs.WorldConfig;
import rocks.cleanstone.game.entity.EntityRegistry;
import rocks.cleanstone.game.entity.EntityRegistryFactory;
import rocks.cleanstone.game.world.chunk.ChunkProvider;
import rocks.cleanstone.game.world.chunk.ChunkProviderFactory;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.RegionManager;
import rocks.cleanstone.game.world.region.RegionManagerFactory;
import rocks.cleanstone.storage.world.WorldDataSource;
import rocks.cleanstone.storage.world.WorldDataSourceCreationException;
import rocks.cleanstone.storage.world.WorldDataSourceFactory;
import rocks.cleanstone.storage.world.WorldDataSourceFactoryRegistry;

import java.io.IOException;

@Slf4j
@Component
public class SimpleWorldLoader implements WorldLoader {
    private final ChunkProviderFactory chunkProviderFactory;
    private final RegionManagerFactory regionManagerFactory;
    private final EntityRegistryFactory entityRegistryFactory;
    private final WorldGeneratorManager worldGeneratorManager;
    private final WorldFactory worldFactory;
    private final WorldDataSourceFactory worldDataSourceFactory;

    @Autowired
    public SimpleWorldLoader(ChunkProviderFactory chunkProviderFactory,
                             RegionManagerFactory regionManagerFactory,
                             EntityRegistryFactory entityRegistryFactory,
                             WorldGeneratorManager worldGeneratorManager,
                             WorldFactory worldFactory,
                             WorldDataSourceFactoryRegistry registry) {
        this.chunkProviderFactory = chunkProviderFactory;
        this.regionManagerFactory = regionManagerFactory;
        this.entityRegistryFactory = entityRegistryFactory;
        this.worldGeneratorManager = worldGeneratorManager;
        this.worldFactory = worldFactory;
        this.worldDataSourceFactory = registry.getWorldDataSourceFactory();
    }

    @Async(value = "worldExec")
    @Override
    public ListenableFuture<World> loadWorld(WorldConfig worldConfig) {

        WorldGenerator worldGenerator = worldGeneratorManager.getWorldGenerator(worldConfig.getGenerator());
        if (worldGenerator == null) {
            return AsyncResult.forExecutionException(
                    new IllegalArgumentException("Cannot find worldGenerator " + worldConfig.getGenerator()));
        }

        WorldDataSource worldDataSource;
        try {
            worldDataSource = worldDataSourceFactory.get(worldConfig.getName());
        } catch (WorldDataSourceCreationException e) {
            return AsyncResult.forExecutionException(new IOException("Failed to create worldDataSource", e));
        }

        ChunkProvider chunkProvider = chunkProviderFactory.get(worldDataSource, worldGenerator);
        RegionManager regionManager = regionManagerFactory.get(chunkProvider);
        EntityRegistry entityRegistry = entityRegistryFactory.get();
        World world = worldFactory.get(worldConfig, worldGenerator, worldDataSource, regionManager, entityRegistry);

        // TODO: Loading spawn and other tasks(?)
        log.info("World '" + worldConfig.getName() + "' loaded");
        return new AsyncResult<>(world);
    }

    @Override
    public void unloadWorld(World world) {
        world.close();
        log.info("World '" + world.getID() + "' unloaded");
    }
}
