package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.io.data.world.LevelDBWorldDataSource;
import rocks.cleanstone.io.data.world.WorldDataSource;

public interface WorldLoader {
    ListenableFuture<World> loadWorld(String id);

    void unloadWorld(String id);

    WorldDataSource getDataSource(String id);
}
