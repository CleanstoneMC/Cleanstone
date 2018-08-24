package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;

import java.io.File;

public interface WorldLoader {
    ListenableFuture<World> loadWorld(String id);

    void unloadWorld(World world);

    File getWorldDataFolder();
}
