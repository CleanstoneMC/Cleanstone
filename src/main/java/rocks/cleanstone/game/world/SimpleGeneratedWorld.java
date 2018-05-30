package rocks.cleanstone.game.world;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.Region;
import rocks.cleanstone.game.world.region.RegionWorker;
import rocks.cleanstone.io.data.world.WorldDataSource;
import rocks.cleanstone.net.minecraft.packet.enums.Difficulty;
import rocks.cleanstone.net.minecraft.packet.enums.Dimension;
import rocks.cleanstone.net.minecraft.packet.enums.LevelType;

import java.util.Collection;
import java.util.Map;

public class SimpleGeneratedWorld implements World {

    private final String id;
    private final WorldDataSource dataSource;
    private final WorldGenerator generator;
    private final Table<Integer, Integer, Region> regions;
    private final Map<Region, Collection<RegionWorker>> regionWorkersMap;
    private Dimension dimension = Dimension.OVERWORLD; //TODO: Move
    private Difficulty difficulty = Difficulty.PEACEFUL; //TODO: Move
    private LevelType levelType = LevelType.FLAT; //TODO: Move
    private Location spawnLocation;

    public SimpleGeneratedWorld(String id, WorldDataSource dataSource, WorldGenerator generator, Location spawnLocation) {
        this.id = id;
        this.dataSource = dataSource;
        this.generator = generator;
        this.spawnLocation = spawnLocation;
        regions = HashBasedTable.create();
        regionWorkersMap = Maps.newConcurrentMap();
    }

    public SimpleGeneratedWorld(String id, WorldDataSource dataSource, WorldGenerator generator) {
        this(id, dataSource, generator, null);
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public LevelType getLevelType() {
        return levelType;
    }

    @Override
    public Location getFirstSpawnLocation() {
        if (spawnLocation == null) {
            spawnLocation = new Location(new Position(0, 46, 0, this), new Rotation(0, 0)); //TODO: Check if y is highest block
        }

        return spawnLocation;
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
