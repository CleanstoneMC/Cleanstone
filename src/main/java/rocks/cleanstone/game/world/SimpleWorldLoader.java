package rocks.cleanstone.game.world;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
public class SimpleWorldLoader implements WorldLoader {
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

        final WorldGenerator worldGenerator = worldGeneratorManager.getWorldGenerator(worldConfig.getGenerator());
        if (worldGenerator == null) {
            return AsyncResult.forExecutionException(
                    new IllegalArgumentException("Cannot find worldGenerator " + worldConfig.getGenerator()));
        }

        final WorldDataSource worldDataSource;
        try {
            worldDataSource = worldDataSourceFactory.get(worldConfig.getName());
        } catch (WorldDataSourceCreationException e) {
            return AsyncResult.forExecutionException(new IOException("Failed to create worldDataSource", e));
        }
        final ChunkProvider chunkProvider = context.getBean(ChunkProvider.class, worldDataSource, worldGenerator);
        final RegionManager regionManager = context.getBean(RegionManager.class, chunkProvider);
        final EntityRegistry entityRegistry = context.getBean(EntityRegistry.class);
        final World world = context.getBean(World.class, worldConfig, worldGenerator, worldDataSource, regionManager, entityRegistry);

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
