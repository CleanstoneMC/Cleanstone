package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;

public interface WorldManager {

    Collection<World> getLoadedWorlds();

    World getLoadedWorld(String id);

    ListenableFuture<World> loadWorld(String id);

    void unloadWorld(String id);

    void createWorld(String id, WorldGenerator generator);

    void deleteWorld(String id);

}
