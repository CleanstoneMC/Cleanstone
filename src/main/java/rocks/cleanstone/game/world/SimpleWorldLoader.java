package rocks.cleanstone.game.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.chunk.ChunkProvider;
import rocks.cleanstone.game.world.data.LevelDBWorldDataSourceFactory;
import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.RegionManager;

import java.io.File;
import java.io.IOException;

@Component
public class SimpleWorldLoader implements WorldLoader {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ApplicationContext context;
    private final WorldGeneratorManager worldGeneratorManager;
    private final LevelDBWorldDataSourceFactory levelDBWorldDataSourceFactory;

    @Autowired
    public SimpleWorldLoader(ApplicationContext context, WorldGeneratorManager worldGeneratorManager, LevelDBWorldDataSourceFactory levelDBWorldDataSourceFactory) {
        this.context = context;
        this.worldGeneratorManager = worldGeneratorManager;
        this.levelDBWorldDataSourceFactory = levelDBWorldDataSourceFactory;
    }

    @Async(value = "worldExec")
    @Override
    public ListenableFuture<World> loadWorld(String id) {
        logger.info("Loading world '" + id + "'...");
        // TODO Fetch generator from dataSource

        WorldGenerator worldGenerator = worldGeneratorManager.getWorldGenerator("mountainWorldGenerator");

        if (worldGenerator == null) {
            logger.error("Cannot find Worldgenerator - Exiting");
            System.exit(0);
        }

        WorldDataSource worldDataSource;
        try {
            worldDataSource = levelDBWorldDataSourceFactory.get(getWorldDataFolder(), id);
        } catch (IOException e) {
            logger.error("Could not get World Datasource", e);
            return new AsyncResult<>(null);
        }
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
