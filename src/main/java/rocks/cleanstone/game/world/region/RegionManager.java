package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Nullable;
import java.util.Collection;

public interface RegionManager {
    Collection<Region> getLoadedRegions();

    @Nullable
    Region getLoadedRegion(int x, int y);

    ListenableFuture<Region> loadRegion(int x, int y);

    void unloadRegion(int x, int y);
}
