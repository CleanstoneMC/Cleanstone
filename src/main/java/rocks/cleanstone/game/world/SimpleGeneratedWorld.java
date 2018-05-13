package rocks.cleanstone.game.world;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.Region;
import rocks.cleanstone.game.world.region.RegionWorker;
import rocks.cleanstone.io.data.world.WorldDataSource;

import java.util.Collection;
import java.util.Map;

public class SimpleGeneratedWorld implements World {

    private final String id;
    private final WorldDataSource dataSource;
    private final WorldGenerator generator;
    private final Table<Integer, Integer, Region> regions;
    private final Map<Region, Collection<RegionWorker>> regionWorkersMap;

    public SimpleGeneratedWorld(String id, WorldDataSource dataSource, WorldGenerator generator) {
        this.id = id;
        this.dataSource = dataSource;
        this.generator = generator;
        regions = HashBasedTable.create();
        regionWorkersMap = Maps.newConcurrentMap();
    }

    @Override
    public String getID() {
        return id;
    }

    public WorldGenerator getGenerator() {
        return generator;
    }

    @Override
    public Collection<Region> getLoadedRegions() {
        return regions.values();
    }

    @Override
    public Region getLoadedRegion(int x, int y) {
        return regions.get(x, y);
    }

    @Override
    public ListenableFuture<Region> loadRegion(int x, int y) {
        // TODO
        return null;
    }

    @Override
    public void unloadRegion(int x, int y) {
        // TODO
    }
}
