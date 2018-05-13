package rocks.cleanstone.game;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.CleanstoneMainServer;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.WorldLoader;
import rocks.cleanstone.game.world.generation.WorldGenerator;

import javax.annotation.Nullable;
import java.util.Collection;

public class SimpleOpenWorldGame implements OpenWorldGame {

    private final CleanstoneMainServer server;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Collection<World> loadedWorlds = Sets.newConcurrentHashSet();
    private final WorldLoader worldLoader;

    public SimpleOpenWorldGame(CleanstoneMainServer server, WorldLoader worldLoader) {
        this.server = server;
        this.worldLoader = worldLoader;
        for (String autoLoadWorldID : server.getMinecraftConfig().getAutoLoadWorlds()) {
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
        return worldLoader.loadWorld(id);
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

    public CleanstoneMainServer getServer() {
        return server;
    }
}
