package rocks.cleanstone.game.world.region;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.chunk.ChunkProvider;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Divides the world into equal quadratic regions
 */
public class QuadraticRegionManager implements RegionManager {

    private final Map<Pair<Integer, Integer>, Region> regions;
    private final ChunkProvider chunkProvider;
    private final int chunksPerRegionRoot = 16; // TODO make this customizable

    public QuadraticRegionManager(ChunkProvider chunkProvider) {
        this.chunkProvider = chunkProvider;
        regions = new HashMap<>();
    }

    @Override
    public Collection<Region> getLoadedRegions() {
        return ImmutableSet.copyOf(regions.values());
    }

    @Nullable
    @Override
    public Region getLoadedRegion(int chunkX, int chunkZ) {
        return regions.get(getRegionCoordinates(chunkX, chunkZ));
    }

    @Override
    public ListenableFuture<Region> loadRegion(int chunkX, int chunkZ) {
        Pair<Integer, Integer> coordPair = getRegionCoordinates(chunkX, chunkZ);

        Region region = new SimpleRegion("QuR[" + coordPair.getLeft() + ":" + coordPair.getRight() + "]",
                new LocalRegionWorker(), chunkProvider);
        regions.put(coordPair, region);
        return new AsyncResult<>(region);
    }

    @Override
    public ListenableFuture<Region> getRegion(int chunkX, int chunkZ) {
        Pair<Integer, Integer> coordPair = getRegionCoordinates(chunkX, chunkZ);
        if (regions.containsKey(coordPair)) {
            return new AsyncResult<>(regions.get(coordPair));
        }

        return loadRegion(coordPair.getLeft(), coordPair.getRight());
    }

    @Override
    public void unloadRegion(Region region) {
        regions.values().remove(region);
    }

    private Pair<Integer, Integer> getRegionCoordinates(int chunkX, int chunkZ) {
        int x = chunkX >> 4;
        int y = chunkZ >> 4;
        return Pair.of(x, y);
    }

    @Override
    public void close() {
        regions.values().forEach(Region::unloadAllChunks);
    }
}
