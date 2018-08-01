package rocks.cleanstone.game.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.world.chunk.ChunkProvider;
import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.RegionManager;

import java.io.File;

public class SimpleWorldLoader implements WorldLoader {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ApplicationContext context;
    private final MaterialRegistry materialRegistry;

    @Autowired
    public SimpleWorldLoader(ApplicationContext context, MaterialRegistry materialRegistry) {
        this.context = context;
        this.materialRegistry = materialRegistry;
    }

    @Async(value = "worldLoadingExec")
    @Override
    public ListenableFuture<World> loadWorld(String id) {
        logger.info("Loading world '" + id + "'...");
        // TODO Fetch generator from dataSource

        WorldGenerator worldGenerator = context.getBean(WorldGenerator.class, materialRegistry, 234892734);
        WorldDataSource worldDataSource = context.getBean(WorldDataSource.class, getWorldDataFolder(), id, materialRegistry);
        ChunkProvider chunkProvider = context.getBean(ChunkProvider.class, worldDataSource, worldGenerator);
        RegionManager regionManager = context.getBean(RegionManager.class, chunkProvider);
        World world = context.getBean(World.class, id, worldGenerator, worldDataSource, regionManager);

        // TODO: Loading spawn and other tasks(?)
        logger.info("World '" + id + "' loaded.");
        return new AsyncResult<>(world);
    }

    @Override
    public void unloadWorld(World world) {
        logger.info("Unloading world '" + world.getID() + "'...");

        world.close();

        logger.info("World '" + world.getID() + "' unloaded.");
    }

    @Override
    public File getWorldDataFolder() {
        File dataFolder = new File("data");
        try {
            dataFolder.mkdir();
        } catch (SecurityException e) {
            logger.error("Cannot create data folder (no permission?)", e);
        }
        return dataFolder;
    }
}
