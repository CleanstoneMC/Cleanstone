package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;

import javax.annotation.Nullable;

/**
 * Manages the regions that divide the world into independent areas
 */
public interface RegionManager {
    Collection<Region> getLoadedRegions();

    @Nullable
    Region getLoadedRegion(int chunkX, int chunkY);

    ListenableFuture<Region> loadRegion(int chunkX, int chunkY);

    ListenableFuture<Region> getRegion(int chunkX, int chunkY);

    void unloadRegion(Region region);

    void close();
}
