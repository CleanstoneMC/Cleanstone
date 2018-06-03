package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.IOException;

import rocks.cleanstone.game.world.data.WorldDataSource;

public interface WorldLoader {
    ListenableFuture<World> loadWorld(String id);

    void unloadWorld(String id);

    WorldDataSource getDataSource(String id) throws IOException;

    File getWorldDataFolder();
}
