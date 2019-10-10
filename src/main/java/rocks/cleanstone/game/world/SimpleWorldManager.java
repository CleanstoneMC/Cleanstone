package rocks.cleanstone.game.world;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.config.structs.WorldConfig;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SimpleWorldManager implements WorldManager {
    private final WorldLoader worldLoader;
    private final Map<WorldConfig, World> worldMap = new ConcurrentHashMap<>();

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

    @Nullable
    @Override
    public World getLoadedWorld(String worldID) {
        return getLoadedWorlds().stream()
                .filter(world -> world.getID().equals(worldID))
                .findAny().orElse(null);
    }

    @Override
    public ListenableFuture<World> loadWorld(WorldConfig worldConfig) {
        ListenableFuture<World> worldListenableFuture = worldLoader.loadWorld(worldConfig);

        worldListenableFuture.addCallback(world -> {
            Preconditions.checkNotNull(world, "Loaded world " + worldConfig.getName() + " cannot be null");
            worldMap.put(world.getWorldConfig(), world);
        }, throwable -> log.error("Error while loading world " + worldConfig.getName(), throwable));

        return worldListenableFuture;
    }

    @Override
    public void unloadWorld(WorldConfig worldConfig) {
        try {
            World world = worldMap.get(worldConfig);

            if (world == null) {
                throw new NullPointerException("World " + worldConfig + " not found");
            }

            worldLoader.unloadWorld(world);

            worldMap.remove(worldConfig);
        } catch (Exception e) {
            log.error("Error occurred while unloading World", e);
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
