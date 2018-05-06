package rocks.cleanstone.game.world;

import java.util.Collection;

public interface WorldManager {

    Collection<World> getLoadedWorlds();

    World getLoadedWorld(String id);

    World loadWorld(String id);

    void unloadWorld(String id);

    void createWorld(String id, WorldGenerator generator);

    void deleteWorld(String id);

}
