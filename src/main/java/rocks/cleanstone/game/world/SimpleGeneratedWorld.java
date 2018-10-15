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
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.Region;
import rocks.cleanstone.game.world.region.RegionManager;
import rocks.cleanstone.net.minecraft.packet.enums.Difficulty;
import rocks.cleanstone.net.minecraft.packet.enums.Dimension;
import rocks.cleanstone.net.minecraft.packet.enums.LevelType;

import javax.annotation.Nullable;
import java.util.concurrent.ExecutionException;

@Component
@Scope("prototype")
public class SimpleGeneratedWorld implements World {

    protected final WorldGenerator generator;
    protected final WorldDataSource dataSource;
    protected final RegionManager regionManager;
    protected final EntityRegistry entityRegistry;
    protected final WorldConfig worldConfig;
    private final Dimension dimension = Dimension.OVERWORLD; //TODO: Move
    private final Difficulty difficulty = Difficulty.PEACEFUL; //TODO: Move
    private final LevelType levelType = LevelType.FLAT; //TODO: Move
    private RotatablePosition spawnPosition;
    private final Logger logger = LoggerFactory.getLogger(getClass());

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
            spawnPosition = generator.getFirstSpawnPosition(worldConfig.getSeed());
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

        final int relX = getRelativeBlockCoordinate(x);
        final int relZ = getRelativeBlockCoordinate(z);
        try {
            return new AsyncResult<>(getChunk(ChunkCoords.ofBlockCoords(x, z)).get().getBlock(relX, y, relZ));
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
        final ChunkCoords chunkCoords = ChunkCoords.ofBlockCoords(x, z);
        final int relX = getRelativeBlockCoordinate(x);
        final int relZ = getRelativeBlockCoordinate(z);

        getChunk(chunkCoords).addCallback(chunk -> chunk.setBlock(relX, y, relZ, block), throwable -> logger.error("Failed to get chunk " + chunkCoords + " in world " + worldConfig.getName(), throwable));
    }

    @Override
    public void setBlockAt(Position position, Block block) {
        setBlockAt(position.getXAsInt(), position.getYAsInt(), position.getZAsInt(), block);
    }

    @Override
    public ListenableFuture<Chunk> getChunk(ChunkCoords coords) {
        try {
            return new AsyncResult<>(getRegion(coords).get().getChunk(coords).get());
        } catch (InterruptedException | ExecutionException e) {
            return AsyncResult.forExecutionException(e);
        }
    }

    @Nullable
    @Override
    public Chunk getLoadedChunk(ChunkCoords coords) {
        final Region region = regionManager.getLoadedRegion(coords);

        if (region == null) {
            return null;
        }

        return region.getLoadedChunk(coords);
    }

    @Override
    public ListenableFuture<Chunk> getChunkAt(Position position) {
        return getChunk(ChunkCoords.of(position));
    }

    @Nullable
    @Override
    public Chunk getLoadedChunkAt(Position position) {
        return getLoadedChunk(ChunkCoords.of(position));
    }

    @Override
    public void unloadRegions() {
        regionManager.getLoadedRegions().forEach(regionManager::unloadRegion);
    }

    @Override
    public void delete() {
        dataSource.drop();
    }

    private ListenableFuture<Region> getRegion(ChunkCoords coords) {
        return regionManager.getRegion(coords);
    }

    @Override
    public void close() {
        regionManager.close();
        getDataSource().close();
    }
}
