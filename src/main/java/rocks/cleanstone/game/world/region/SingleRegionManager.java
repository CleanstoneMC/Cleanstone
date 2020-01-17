package rocks.cleanstone.game.world.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.game.world.chunk.ChunkProvider;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;

/**
 * Manages a single region in the world to rule them all
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
    public Region getLoadedRegion(ChunkCoords coords) {
        return region;
    }

    @Async(value = "worldExec")
    @Override
    public ListenableFuture<Region> loadRegion(ChunkCoords coords) {
        region = new SimpleRegion("SingleR", new LocalRegionWorker(), chunkProvider);
        return new AsyncResult<>(region);
    }

    @Async(value = "worldExec")
    @Override
    public ListenableFuture<Region> getRegion(ChunkCoords coords) {
        Region region = getLoadedRegion(coords);
        if (region != null) {
            return new AsyncResult<>(region);
        }
        return loadRegion(coords);
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
