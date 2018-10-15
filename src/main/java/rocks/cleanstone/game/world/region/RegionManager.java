package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.chunk.ChunkCoords;

import javax.annotation.Nullable;
import java.util.Collection;

/**
 * Manages the regions that divide the world into independent areas
 */
public interface RegionManager {
    Collection<Region> getLoadedRegions();

    @Nullable
    Region getLoadedRegion(ChunkCoords coords);

    ListenableFuture<Region> loadRegion(ChunkCoords coords);

    ListenableFuture<Region> getRegion(ChunkCoords coords);

    void unloadRegion(Region region);

    void close();
}
