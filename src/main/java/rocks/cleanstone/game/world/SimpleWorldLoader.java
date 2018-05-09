package rocks.cleanstone.game.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.generation.SimpleWorldGenerator;
import rocks.cleanstone.io.data.world.WorldDataSource;

public class SimpleWorldLoader implements WorldLoader {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final WorldDataSource worldDataSource;

    public SimpleWorldLoader(WorldDataSource worldDataSource) {
        this.worldDataSource = worldDataSource;
    }

    @Async(value = "worldLoadingExec")
    @Override
    public ListenableFuture<World> loadWorld(String id) {
        logger.info("Loading world '" + id + "'...");
        World world = new SimpleGeneratedWorld(id, worldDataSource, new SimpleWorldGenerator());
        // TODO: Loading spawn and other tasks(?)
        logger.info("World '" + id + "' loaded.");
        return new AsyncResult<>(world);
    }
}
