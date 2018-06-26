package rocks.cleanstone.game.world;

import com.google.common.base.Preconditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

import javax.annotation.Nullable;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.Region;
import rocks.cleanstone.game.world.region.RegionManager;
import rocks.cleanstone.game.world.region.chunk.Chunk;
import rocks.cleanstone.game.world.region.chunk.ChunkProvider;
import rocks.cleanstone.game.world.region.chunk.SimpleChunkProvider;
import rocks.cleanstone.net.packet.enums.Difficulty;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;
import rocks.cleanstone.utils.Vector;

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
    private Logger logger = LoggerFactory.getLogger(getClass());

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

    @Override
    public Block getBlockAt(int x, int y, int z) {
        Preconditions.checkArgument(y < Chunk.HEIGHT && y >= 0,
                "Coordinate y (" + y + ") is not in allowed range (0<=y<" + Chunk.HEIGHT + ")");
        Chunk chunk;
        try {
            chunk = getChunkProvider().getChunk(x / 16, z / 16).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to get chunk " + x / 16 + ":" + z / 16 + " in world " + id, e);
        }
        return chunk.getBlock(x, y, z);
    }

    @Override
    public Block getBlockAt(Vector vector) {
        return getBlockAt((int) vector.getX(), (int) vector.getY(), (int) vector.getZ());
    }

    @Override
    public void setBlockAt(int x, int y, int z, Block block) {
        Preconditions.checkArgument(y < Chunk.HEIGHT && y >= 0,
                "Coordinate y (" + y + ") is not in allowed range (0<=y<" + Chunk.HEIGHT + ")");
        Preconditions.checkNotNull(block, "block cannot be null");
        // TODO access region chunk cache
        chunkProvider.getChunk(x / 16, z / 16).addCallback(chunk -> {
            chunk.setBlock(x, y, z, block);

            //TODO: Do this in an Async Task
            chunkProvider.getDataSource().saveChunk(chunk);
        }, throwable -> {
            logger.error("Failed to set block " + block + " at " + x + ":" + y + ":" + z
                    + " in world " + id, throwable);
        });
    }

    @Override
    public void setBlockAt(Vector vector, Block block) {
        setBlockAt((int) vector.getX(), (int) vector.getY(), (int) vector.getZ(), block);
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
