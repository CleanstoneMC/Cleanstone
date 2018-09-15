package rocks.cleanstone.game.world;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.config.WorldConfig;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.entity.EntityRegistry;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.Region;
import rocks.cleanstone.game.world.region.RegionManager;
import rocks.cleanstone.net.packet.enums.Difficulty;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;

import java.util.concurrent.ExecutionException;

@Component
@Scope("prototype")
public class SimpleGeneratedWorld implements World {

    protected final WorldGenerator generator;
    protected final WorldDataSource dataSource;
    protected final RegionManager regionManager;
    protected final EntityRegistry entityRegistry;
    protected final WorldConfig worldConfig;
    private Dimension dimension = Dimension.OVERWORLD; //TODO: Move
    private Difficulty difficulty = Difficulty.PEACEFUL; //TODO: Move
    private LevelType levelType = LevelType.FLAT; //TODO: Move
    private RotatablePosition spawnPosition;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public SimpleGeneratedWorld(WorldConfig worldConfig, WorldGenerator generator, WorldDataSource dataSource,
                                RegionManager regionManager, EntityRegistry entityRegistry,
                                RotatablePosition spawnPosition) {
        this.worldConfig = worldConfig;
        this.generator = generator;
        this.dataSource = dataSource;
        this.regionManager = regionManager;
        this.entityRegistry = entityRegistry;
        this.spawnPosition = spawnPosition;
    }

    public SimpleGeneratedWorld(WorldConfig worldConfig, WorldGenerator generator, WorldDataSource dataSource,
                                RegionManager regionManager, EntityRegistry entityRegistry) {
        this(worldConfig, generator, dataSource, regionManager, entityRegistry, null);
    }

    private static int getRelativeBlockCoordinate(int blockCoordinate) {
        int relX;
        if (blockCoordinate > 0) {
            relX = blockCoordinate % Chunk.WIDTH;
        } else {
            relX = Chunk.WIDTH + blockCoordinate % Chunk.WIDTH;
            if (relX == Chunk.WIDTH) relX = 0;
        }
        return relX;
    }

    private static int getChunkCoordinate(int blockCoordinate) {
        return blockCoordinate >> 4;
    }

    @Override
    public String getID() {
        return worldConfig.getName();
    }

    @Override
    public WorldConfig getWorldConfig() {
        return worldConfig;
    }

    @Override
    public WorldGenerator getWorldGenerator() {
        return generator;
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
    public RotatablePosition getFirstSpawnPosition() {
        if (spawnPosition == null) {
            spawnPosition = new RotatablePosition(new Position(0, generator.getHeightAt(worldConfig.getSeed(), 0, 0) + 1, 0), new Rotation(0, 0));
        }
        return spawnPosition;
    }

    @Override
    public WorldDataSource getDataSource() {
        return dataSource;
    }

    @Override
    public EntityRegistry getEntityRegistry() {
        return entityRegistry;
    }

    @Async(value = "worldExec")
    @Override
    public ListenableFuture<Block> getBlockAt(int x, int y, int z) {
        Preconditions.checkArgument(y < Chunk.HEIGHT && y >= 0,
                "Coordinate y (" + y + ") is not in allowed range (0<=y<" + Chunk.HEIGHT + ")");

        int chunkX = getChunkCoordinate(x), chunkZ = getChunkCoordinate(z);
        int relX = getRelativeBlockCoordinate(x), relZ = getRelativeBlockCoordinate(z);
        try {
            return new AsyncResult<>(getChunk(chunkX, chunkZ).get().getBlock(relX, y, relZ));
        } catch (InterruptedException | ExecutionException e) {
            return AsyncResult.forExecutionException(e);
        }
    }

    @Async(value = "worldExec")
    @Override
    public ListenableFuture<Block> getBlockAt(Position position) {
        return getBlockAt(position.getXAsInt(), position.getYAsInt(), position.getZAsInt());
    }

    @Override
    public void setBlockAt(int x, int y, int z, Block block) {
        Preconditions.checkArgument(y < Chunk.HEIGHT && y >= 0,
                "Coordinate y (" + y + ") is not in allowed range (0<=y<" + Chunk.HEIGHT + ")");
        Preconditions.checkNotNull(block, "block cannot be null");

        int chunkX = getChunkCoordinate(x), chunkZ = getChunkCoordinate(z);
        int relX = getRelativeBlockCoordinate(x), relZ = getRelativeBlockCoordinate(z);

        getChunk(chunkX, chunkZ).addCallback(chunk -> {
            chunk.setBlock(relX, y, relZ, block);
        }, throwable -> {
            logger.error("Failed to get chunk " + chunkX + ":" + chunkZ + " in world " + worldConfig.getName(), throwable);
        });
    }

    @Override
    public void setBlockAt(Position position, Block block) {
        setBlockAt(position.getXAsInt(), position.getYAsInt(), position.getZAsInt(), block);
    }

    @Override
    public ListenableFuture<Chunk> getChunk(int chunkX, int chunkZ) {
        try {
            return new AsyncResult<>(getRegion(chunkX, chunkZ).get().getChunk(chunkX, chunkZ).get());
        } catch (InterruptedException | ExecutionException e) {
            return AsyncResult.forExecutionException(e);
        }
    }

    @Override
    public ListenableFuture<Chunk> getChunkAt(Position position) {
        int chunkX = getChunkCoordinate(position.getXAsInt());
        int chunkZ = getChunkCoordinate(position.getZAsInt());
        return getChunk(chunkX, chunkZ);
    }

    @Override
    public void unloadRegions() {
        regionManager.getLoadedRegions().forEach(regionManager::unloadRegion);
    }

    private ListenableFuture<Region> getRegion(int chunkX, int chunkZ) {
        return regionManager.getRegion(chunkX, chunkZ);
    }

    @Override
    public void close() {
        regionManager.close();
        getDataSource().close();
    }
}
