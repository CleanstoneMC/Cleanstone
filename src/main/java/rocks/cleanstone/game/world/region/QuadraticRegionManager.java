package rocks.cleanstone.game.world.region;

import com.google.common.collect.ImmutableSet;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import rocks.cleanstone.game.world.chunk.ChunkProvider;

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
    public Region getLoadedRegion(int chunkX, int chunkY) {
        return regions.get(getRegionCoordinates(chunkX, chunkY));
    }

    @Override
    public ListenableFuture<Region> loadRegion(int chunkX, int chunkY) {
        Pair<Integer, Integer> coordPair = getRegionCoordinates(chunkX, chunkY);

        Region region = new SimpleRegion("QuR[" + coordPair.getLeft() + ":" + coordPair.getRight() + "]",
                new LocalRegionWorker(), chunkProvider);
        regions.put(coordPair, region);
        return new AsyncResult<>(region);
    }

    @Override
    public ListenableFuture<Region> getRegion(int chunkX, int chunkY) {
        Pair<Integer, Integer> coordPair = getRegionCoordinates(chunkX, chunkY);
        if (regions.containsKey(coordPair)) {
            return new AsyncResult<>(regions.get(coordPair));
        }

        return loadRegion(coordPair.getLeft(), coordPair.getRight());
    }

    @Override
    public void unloadRegion(Region region) {
        regions.values().remove(region);
    }

    private Pair<Integer, Integer> getRegionCoordinates(int chunkX, int chunkY) {
        int x = chunkX >> 4;
        int y = chunkY >> 4;
        return Pair.of(x, y);
    }

    @Override
    public void close() {
        regions.values().forEach(Region::unloadAllChunks);
    }
}
