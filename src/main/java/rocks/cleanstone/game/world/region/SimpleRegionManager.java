package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Nullable;
import java.util.Collection;

public class SimpleRegionManager implements RegionManager {
    @Override
    public Collection<Region> getLoadedRegions() {
        return null;
    }

    @Nullable
    @Override
    public Region getLoadedRegion(int x, int y) {
        return null;
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
