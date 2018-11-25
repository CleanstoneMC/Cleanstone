package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;

import javax.annotation.Nullable;

import rocks.cleanstone.core.config.WorldConfig;

public interface WorldManager {

    Collection<World> getLoadedWorlds();

    @Nullable
    World getLoadedWorld(WorldConfig worldConfig);

    @Nullable
    World getLoadedWorld(String worldID);

    ListenableFuture<World> loadWorld(WorldConfig worldConfig);

    boolean isWorldLoaded(WorldConfig worldConfig);

    void unloadWorld(WorldConfig worldConfig);

    void createWorld(WorldConfig worldConfig);

    void deleteWorld(WorldConfig worldConfig);

}
