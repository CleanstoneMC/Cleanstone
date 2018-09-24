package rocks.cleanstone.game.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.config.WorldConfig;
import rocks.cleanstone.game.entity.EntityRegistry;
import rocks.cleanstone.game.world.chunk.ChunkProvider;
import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.data.WorldDataSourceCreationException;
import rocks.cleanstone.game.world.data.WorldDataSourceFactory;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.RegionManager;

import java.io.IOException;

@Component
public class SimpleWorldLoader implements WorldLoader {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ApplicationContext context;
    private final WorldGeneratorManager worldGeneratorManager;
    private final WorldDataSourceFactory worldDataSourceFactory;

    @Autowired
    public SimpleWorldLoader(ApplicationContext context, WorldGeneratorManager worldGeneratorManager, WorldDataSourceFactory worldDataSourceFactory) {
        this.context = context;
        this.worldGeneratorManager = worldGeneratorManager;
        this.worldDataSourceFactory = worldDataSourceFactory;
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
        logger.info("World '" + worldConfig.getName() + "' loaded");
        return new AsyncResult<>(world);
    }

    @Override
    public void unloadWorld(World world) {
        world.close();
        logger.info("World '" + world.getID() + "' unloaded");
    }
}
