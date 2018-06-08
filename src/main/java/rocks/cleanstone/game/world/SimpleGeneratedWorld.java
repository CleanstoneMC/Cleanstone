package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.Region;
import rocks.cleanstone.game.world.region.RegionManager;
import rocks.cleanstone.net.packet.enums.Difficulty;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;

import javax.annotation.Nullable;
import java.util.Collection;

public class SimpleGeneratedWorld implements World {

    private final String id;
    private final WorldDataSource dataSource;
    private final WorldGenerator generator;
    private final RegionManager regionManager;
    private Dimension dimension = Dimension.OVERWORLD; //TODO: Move
    private Difficulty difficulty = Difficulty.PEACEFUL; //TODO: Move
    private LevelType levelType = LevelType.FLAT; //TODO: Move
    private Location spawnLocation;

    public SimpleGeneratedWorld(String id, WorldDataSource dataSource, WorldGenerator generator, RegionManager regionManager, Location spawnLocation) {
        this.id = id;
        this.dataSource = dataSource;
        this.generator = generator;
        this.regionManager = regionManager;
        this.spawnLocation = spawnLocation;
    }

    public SimpleGeneratedWorld(String id, WorldDataSource dataSource, WorldGenerator generator, RegionManager regionManager) {
        this(id, dataSource, generator, regionManager, null);
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


    public Collection<Region> getLoadedRegions() {
        return regionManager.getLoadedRegions();
    }

    @Nullable
    public Region getLoadedRegion(int x, int y) {
        return regionManager.getLoadedRegion(x, y);
    }

    public ListenableFuture<Region> loadRegion(int x, int y) {
        return regionManager.loadRegion(x, y);
    }

    public void unloadRegion(int x, int y) {
        regionManager.unloadRegion(x, y);
    }
}
