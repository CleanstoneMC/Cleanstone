package rocks.cleanstone.game.world.region;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class SimpleRegionManager implements RegionManager {

    private final Map<Pair<Integer, Integer>, Region> regions;

    public SimpleRegionManager(AsyncListenableTaskExecutor localWorkerExecutor) {
        regions = new HashMap<>();
    }

    @Override
    public Collection<Region> getLoadedRegions() {
        return null;
    }

    @Nullable
    @Override
    public Region getLoadedRegion(int x, int y) {
        return regions.get(Pair.of(x, y));
    }

    @Override
    public ListenableFuture<Region> loadRegion(int x, int y) {
        return null;
    }

    @Override
    public ListenableFuture<Region> getRegion(int x, int y) {
        return null;
    }

    @Override
    public void unloadRegion(int x, int y) {

    }
}
