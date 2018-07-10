package rocks.cleanstone.game.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.IOException;

import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.world.chunk.ChunkProvider;
import rocks.cleanstone.game.world.chunk.SimpleChunkProvider;
import rocks.cleanstone.game.world.data.LevelDBWorldDataSource;
import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.FlatWorldGenerator;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.SingleRegionManager;

public class SimpleWorldLoader implements WorldLoader {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final AsyncListenableTaskExecutor worldExecutor;
    private final MaterialRegistry materialRegistry;

    @Autowired
    public SimpleWorldLoader(AsyncListenableTaskExecutor worldExec, MaterialRegistry materialRegistry) {
        this.worldExecutor = worldExec;
        this.materialRegistry = materialRegistry;
    }

    @Async(value = "worldLoadingExec")
    @Override
    public ListenableFuture<World> loadWorld(String id) {
        logger.info("Loading world '" + id + "'...");
        WorldDataSource dataSource;
        try {
            dataSource = getDataSource(id);
        } catch (IOException e) {
            return AsyncResult.forExecutionException(e);
        }

        // TODO Fetch generator from dataSource

        WorldGenerator generator = new FlatWorldGenerator(materialRegistry);
        ChunkProvider chunkProvider = new SimpleChunkProvider(dataSource, generator, worldExecutor);

        World world = new SimpleGeneratedWorld(id, chunkProvider, generator, dataSource,
                new SingleRegionManager(chunkProvider), worldExecutor);

        // TODO: Loading spawn and other tasks(?)
        logger.info("World '" + id + "' loaded.");
        return new AsyncResult<>(world);
    }

    @Override
    public void unloadWorld(World world) {
        logger.info("Unloading world '" + world.getID() + "'...");

        world.getDataSource().close();

        logger.info("World '" + world.getID() + "' unloaded.");
    }

    public WorldDataSource getDataSource(String id) throws IOException {
        return new LevelDBWorldDataSource(getWorldDataFolder(), id, materialRegistry);
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
