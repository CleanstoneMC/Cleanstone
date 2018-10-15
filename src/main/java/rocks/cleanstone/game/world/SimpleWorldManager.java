package rocks.cleanstone.game.world;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.config.WorldConfig;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SimpleWorldManager implements WorldManager {

    private final WorldLoader worldLoader;
    private final Map<WorldConfig, World> worldMap = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public SimpleWorldManager(WorldLoader worldLoader) {
        this.worldLoader = worldLoader;
    }

    @Override
    public Collection<World> getLoadedWorlds() {
        return worldMap.values();
    }

    @Override
    public boolean isWorldLoaded(WorldConfig worldConfig) {
        return worldMap.containsKey(worldConfig);
    }

    @Nullable
    @Override
    public World getLoadedWorld(WorldConfig worldConfig) {
        return worldMap.get(worldConfig);
    }

    @Override
    public ListenableFuture<World> loadWorld(WorldConfig worldConfig) {
        final ListenableFuture<World> worldListenableFuture = worldLoader.loadWorld(worldConfig);

        worldListenableFuture.addCallback(world -> {
            Preconditions.checkNotNull(world, "Loaded world " + worldConfig.getName() + " cannot be null");
            worldMap.put(world.getWorldConfig(), world);
        }, throwable -> logger.error("Error while loading world " + worldConfig.getName(), throwable));

        return worldListenableFuture;
    }

    @Override
    public void unloadWorld(WorldConfig worldConfig) {
        try {
            final World world = worldMap.get(worldConfig);

            if (world == null) {
                throw new NullPointerException("World " + worldConfig + " not found");
            }

            worldLoader.unloadWorld(world);

            worldMap.remove(worldConfig);
        } catch (Exception e) {
            logger.error("Error occurred while unloading World", e);
        }
    }

    @Override
    public void createWorld(WorldConfig worldConfig) {
        // TODO create basic world files
        loadWorld(worldConfig);
    }

    @Override
    public void deleteWorld(WorldConfig worldConfig) {
        unloadWorld(worldConfig);

        //TODO: Delete file
    }
}
