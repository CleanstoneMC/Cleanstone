package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.region.Region;
import rocks.cleanstone.utils.Vector;

import java.util.Collection;

public interface World {
    String getID();

    WorldGenerator getGenerator();

    Collection<Region> getLoadedRegions();

    Region getLoadedRegion(int x, int y, int z);

    ListenableFuture<Region> loadRegion(Vector loc);

    void unloadRegion(int x, int y, int z);
}
