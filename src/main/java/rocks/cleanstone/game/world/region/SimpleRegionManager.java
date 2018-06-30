package rocks.cleanstone.game.world.region;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import rocks.cleanstone.game.world.chunk.ChunkProvider;

public class SimpleRegionManager implements RegionManager {

    private final Map<Pair<Integer, Integer>, Region> regions;
    private final ChunkProvider chunkProvider;

    public SimpleRegionManager(ChunkProvider chunkProvider) {
        this.chunkProvider = chunkProvider;
        regions = new HashMap<>();
    }

    @Override
    public Collection<Region> getLoadedRegions() {
        return regions.values();
    }

    @Nullable
    @Override
    public Region getLoadedRegion(int x, int y) {
        return regions.get(Pair.of(x, y));
    }

    @Override
    public ListenableFuture<Region> loadRegion(int x, int y) {
        Region region = new SimpleRegion(x, y, new LocalRegionWorker(), chunkProvider);

        regions.put(Pair.of(x, y), region);

        return new AsyncResult<>(region);
    }

    @Override
    public ListenableFuture<Region> getRegion(int x, int y) {
        final Pair coordPair = Pair.of(x, y);
        if (regions.containsKey(coordPair)) {
            return new AsyncResult<>(regions.get(coordPair));
        }

        return loadRegion(x, y);
    }

    @Override
    public void unloadRegion(int x, int y) {
        regions.remove(Pair.of(x, y));
    }
}
