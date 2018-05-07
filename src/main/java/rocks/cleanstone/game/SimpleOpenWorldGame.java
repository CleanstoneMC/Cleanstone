package rocks.cleanstone.game;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.CleanstoneMainServer;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.WorldGenerator;

import javax.annotation.Nullable;
import java.util.Collection;

public class SimpleOpenWorldGame implements OpenWorldGame {

    private final CleanstoneMainServer server;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public SimpleOpenWorldGame(CleanstoneMainServer server) {
        this.server = server;
        for (String autoLoadWorldID : server.getMinecraftConfig().getAutoLoadWorlds()) {
            loadWorld(autoLoadWorldID);
        }
    }

    private final Collection<World> loadedWorlds = Sets.newConcurrentHashSet();

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
        logger.info("Loading world '" + id + "'");
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

    public CleanstoneMainServer getServer() {
        return server;
    }
}
