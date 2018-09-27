package rocks.cleanstone.game.world.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.chunk.ChunkProvider;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

/**
 * Manages a single region in the world to rule them all
 */
@Component
@Scope("prototype")
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
    public Region getLoadedRegion(int chunkX, int chunkZ) {
        return region;
    }

    @Async(value = "worldExec")
    @Override
    public ListenableFuture<Region> loadRegion(int chunkX, int chunkZ) {
        region = new SimpleRegion("SingleR", new LocalRegionWorker(), chunkProvider);
        return new AsyncResult<>(region);
    }

    @Async(value = "worldExec")
    @Override
    public ListenableFuture<Region> getRegion(int chunkX, int chunkZ) {
        Region region = getLoadedRegion(chunkX, chunkZ);
        if (region != null) {
            return new AsyncResult<>(region);
        }
        return loadRegion(chunkX, chunkZ);
    }

    @Override
    public void unloadRegion(Region region) {
        if (region != null) {
            region.unloadAllChunks();
        }
        this.region = null;
    }

    @Override
    public void close() {
        if (region != null) {
            region.unloadAllChunks();
        }
    }
}
