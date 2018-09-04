package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.config.WorldConfig;

import javax.annotation.Nullable;
import java.util.Collection;

public interface WorldManager {

    Collection<World> getLoadedWorlds();

    @Nullable
    World getLoadedWorld(WorldConfig worldConfig);

    ListenableFuture<World> loadWorld(WorldConfig worldConfig);

    boolean isWorldLoaded(WorldConfig worldConfig);

    void unloadWorld(WorldConfig worldConfig);

    void createWorld(WorldConfig worldConfig);

    void deleteWorld(WorldConfig worldConfig);

}
