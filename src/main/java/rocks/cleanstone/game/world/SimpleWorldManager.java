package rocks.cleanstone.game.world;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.OpenWorldGame;

import javax.annotation.Nullable;
import java.util.Collection;

public class SimpleWorldManager implements WorldManager {

    private final Collection<World> loadedWorlds = Sets.newConcurrentHashSet();
    private final OpenWorldGame game;

    public SimpleWorldManager(OpenWorldGame game) {
        this.game = game;
        for (String autoLoadWorldID : game.getServer().getMinecraftConfig().getAutoLoadWorlds()) {
            loadWorld(autoLoadWorldID);
        }
    }

    @Override
    public Collection<World> getLoadedWorlds() {
        return ImmutableSet.copyOf(loadedWorlds);
    }

    @Nullable
    @Override
    public World getLoadedWorld(String id) {
        return loadedWorlds.stream().filter(w -> w.getID().equals(id)).findFirst().orElse(null);
    }

    @Override
    public ListenableFuture<World> loadWorld(String id) {
        return null;
    }

    @Override
    public void unloadWorld(String id) {

    }

    @Override
    public void createWorld(String id, WorldGenerator generator) {

    }

    @Override
    public void deleteWorld(String id) {

    }
}
