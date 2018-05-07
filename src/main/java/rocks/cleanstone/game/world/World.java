package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.region.Region;

import java.util.Collection;

public interface World {
    String getID();

    WorldGenerator getGenerator();

    Collection<Region> getLoadedRegions();

    Region getLoadedRegion(int x, int y);

    ListenableFuture<Region> loadRegion(int x, int y);

    void unloadRegion(int x, int y);
}
