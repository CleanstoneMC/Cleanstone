package rocks.cleanstone.game.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.IOException;

import rocks.cleanstone.game.world.data.LevelDBWorldDataSource;
import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.FlatWorldGenerator;
import rocks.cleanstone.game.world.region.SimpleRegionManager;

public class SimpleWorldLoader implements WorldLoader {

    private final Logger logger = LoggerFactory.getLogger(getClass());

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
        World world = new SimpleGeneratedWorld(id, dataSource, new FlatWorldGenerator(), new SimpleRegionManager());

        // TODO: Loading spawn and other tasks(?)
        logger.info("World '" + id + "' loaded.");
        return new AsyncResult<>(world);
    }

    @Override
    public void unloadWorld(String id) {

    }

    public WorldDataSource getDataSource(String id) throws IOException {
        return new LevelDBWorldDataSource(getWorldDataFolder(), id);
    }

    @Override
    public File getWorldDataFolder() {
        return new File("data");
    }
}
