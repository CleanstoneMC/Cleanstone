package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.data.WorldDataSource;

import java.io.File;
import java.io.IOException;

public interface WorldLoader {
    ListenableFuture<World> loadWorld(String id);

    void unloadWorld(World world);

    File getWorldDataFolder();
}
