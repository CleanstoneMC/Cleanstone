package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;

public interface WorldLoader {
    ListenableFuture<World> loadWorld(String id);
}
