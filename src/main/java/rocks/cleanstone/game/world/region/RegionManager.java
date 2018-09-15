package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Nullable;
import java.util.Collection;

/**
 * Manages the regions that divide the world into independent areas
 */
public interface RegionManager {
    Collection<Region> getLoadedRegions();

    @Nullable
    Region getLoadedRegion(int chunkX, int chunkZ);

    ListenableFuture<Region> loadRegion(int chunkX, int chunkZ);

    ListenableFuture<Region> getRegion(int chunkX, int chunkZ);

    void unloadRegion(Region region);

    void close();
}
