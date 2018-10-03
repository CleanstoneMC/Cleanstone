package rocks.cleanstone.game.world.region;

import com.google.common.collect.ImmutableSet;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import rocks.cleanstone.game.world.chunk.ChunkCoords;
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
    public Region getLoadedRegion(ChunkCoords chunkCoords) {
        return regions.get(getRegionCoordinates(chunkCoords));
    }

    @Override
    public ListenableFuture<Region> loadRegion(ChunkCoords chunkCoords) {
        Pair<Integer, Integer> regionCoords = getRegionCoordinates(chunkCoords);

        Region region = new SimpleRegion("QuR[" + regionCoords.getLeft() + ":" + regionCoords.getRight() + "]",
                new LocalRegionWorker(), chunkProvider);
        regions.put(regionCoords, region);
        return new AsyncResult<>(region);
    }

    @Override
    public ListenableFuture<Region> getRegion(ChunkCoords chunkCoords) {
        Pair<Integer, Integer> regionCoords = getRegionCoordinates(chunkCoords);
        if (regions.containsKey(regionCoords)) {
            return new AsyncResult<>(regions.get(regionCoords));
        }

        return loadRegion(chunkCoords);
    }

    @Override
    public void unloadRegion(Region region) {
        regions.values().remove(region);
    }

    private Pair<Integer, Integer> getRegionCoordinates(ChunkCoords coords) {
        int x = coords.getX() >> 4;
        int z = coords.getZ() >> 4;
        return Pair.of(x, z);
    }

    @Override
    public void close() {
        regions.values().forEach(Region::unloadAllChunks);
    }
}
