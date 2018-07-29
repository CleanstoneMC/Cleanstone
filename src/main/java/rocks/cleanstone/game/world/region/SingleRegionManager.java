package rocks.cleanstone.game.world.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.chunk.ChunkProvider;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

/**
 * Manages a single region in the world to rule them all
 */
public class SingleRegionManager implements RegionManager {

    private final ChunkProvider chunkProvider;
    private Region region;

    @Autowired
    public SingleRegionManager(ChunkProvider chunkProvider) {
        this.chunkProvider = chunkProvider;
    }

    @Override
    public Collection<Region> getLoadedRegions() {
        return region != null ? Collections.singleton(region) : Collections.emptySet();
    }

    @Nullable
    @Override
    public Region getLoadedRegion(int chunkX, int chunkY) {
        return region;
    }

    @Override
    public ListenableFuture<Region> loadRegion(int chunkX, int chunkY) {
        region = new SimpleRegion("SingleR", new LocalRegionWorker(), chunkProvider);
        return new AsyncResult<>(region);
    }

    @Override
    public ListenableFuture<Region> getRegion(int chunkX, int chunkY) {
        Region region = getLoadedRegion(chunkX, chunkY);
        if (region != null) return new AsyncResult<>(region);
        return loadRegion(chunkX, chunkY);
    }

    @Override
    public void unloadRegion(Region region) {
        this.region = null;
    }

    @Override
    public void close() {
        if (region != null) {
            region.unloadAllChunks();
        }
    }
}
