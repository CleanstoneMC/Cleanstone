package rocks.cleanstone.game.world;

import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;

import javax.annotation.Nullable;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.Region;
import rocks.cleanstone.game.world.region.RegionManager;
import rocks.cleanstone.game.world.region.chunk.ChunkProvider;
import rocks.cleanstone.game.world.region.chunk.SimpleChunkProvider;
import rocks.cleanstone.net.packet.enums.Difficulty;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;

public class SimpleGeneratedWorld implements World {

    protected final WorldGenerator generator;
    protected final WorldDataSource dataSource;
    protected final RegionManager regionManager;
    private final String id;
    private final ChunkProvider chunkProvider;
    private Dimension dimension = Dimension.OVERWORLD; //TODO: Move
    private Difficulty difficulty = Difficulty.PEACEFUL; //TODO: Move
    private LevelType levelType = LevelType.FLAT; //TODO: Move
    private Location spawnLocation;

    public SimpleGeneratedWorld(String id, WorldGenerator generator, WorldDataSource dataSource,
                                RegionManager regionManager, Location spawnLocation,
                                AsyncListenableTaskExecutor chunkLoadingExecutor) {
        this.id = id;
        this.generator = generator;
        this.dataSource = dataSource;
        this.regionManager = regionManager;
        this.spawnLocation = spawnLocation;

        chunkProvider = new SimpleChunkProvider(dataSource, generator, chunkLoadingExecutor);
    }

    public SimpleGeneratedWorld(String id, WorldGenerator generator, WorldDataSource dataSource,
                                RegionManager regionManager, AsyncListenableTaskExecutor chunkLoadingExecutor) {
        this(id, generator, dataSource, regionManager, null, chunkLoadingExecutor);
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

    @Override
    public ChunkProvider getChunkProvider() {
        return chunkProvider;
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
