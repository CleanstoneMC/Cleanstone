package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.config.WorldConfig;

public interface WorldLoader {
    ListenableFuture<World> loadWorld(WorldConfig worldConfig);

    void unloadWorld(World world);
}
