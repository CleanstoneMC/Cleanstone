package rocks.cleanstone.game.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

import rocks.cleanstone.game.world.generation.FlatWorldGenerator;
import rocks.cleanstone.io.data.world.LevelDBWorldDataSource;
import rocks.cleanstone.io.data.world.WorldDataSource;

public class SimpleWorldLoader implements WorldLoader {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Async(value = "worldLoadingExec")
    @Override
    public ListenableFuture<World> loadWorld(String id) {
        logger.info("Loading world '" + id + "'...");
        WorldDataSource dataSource = getDataSource(id);

        World world = new SimpleGeneratedWorld(id, dataSource, new FlatWorldGenerator());

        // TODO: Loading spawn and other tasks(?)
        logger.info("World '" + id + "' loaded.");
        return new AsyncResult<>(world);
    }

    @Override
    public void unloadWorld(String id) {

    }

    public WorldDataSource getDataSource(String id) {
        return new LevelDBWorldDataSource(id);
    }
}
