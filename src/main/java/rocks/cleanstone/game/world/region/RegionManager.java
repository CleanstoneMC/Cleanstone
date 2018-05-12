package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;

public interface RegionManager {
    Collection<Region> getLoadedRegions();

    Region getLoadedRegion(int x, int y);

    ListenableFuture<Region> loadRegion(int x, int y);

    void unloadRegion(int x, int y);
}
