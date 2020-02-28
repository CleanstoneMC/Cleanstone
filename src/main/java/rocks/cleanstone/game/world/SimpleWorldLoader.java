package rocks.cleanstone.game.world;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.config.structs.WorldConfig;
import rocks.cleanstone.game.entity.EntityRegistry;
import rocks.cleanstone.game.world.chunk.ChunkProvider;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.RegionManager;
import rocks.cleanstone.storage.world.WorldDataSource;
import rocks.cleanstone.storage.world.WorldDataSourceCreationException;
import rocks.cleanstone.storage.world.WorldDataSourceFactory;
import rocks.cleanstone.storage.world.WorldDataSourceFactoryRegistry;

import java.io.IOException;

@Slf4j
@Component
public class SimpleWorldLoader implements WorldLoader {
    private final ApplicationContext context;
    private final WorldGeneratorManager worldGeneratorManager;
    private final WorldDataSourceFactory worldDataSourceFactory;

    @Autowired
    public SimpleWorldLoader(ApplicationContext context, WorldGeneratorManager worldGeneratorManager, WorldDataSourceFactoryRegistry registry) {
        this.context = context;
        this.worldGeneratorManager = worldGeneratorManager;
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
        ChunkProvider chunkProvider = context.getBean(ChunkProvider.class, worldDataSource, worldGenerator);
        RegionManager regionManager = context.getBean(RegionManager.class, chunkProvider);
        EntityRegistry entityRegistry = context.getBean(EntityRegistry.class);
        World world = context.getBean(World.class, worldConfig, worldGenerator, worldDataSource, regionManager, entityRegistry);

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
