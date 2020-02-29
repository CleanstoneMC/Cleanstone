package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.enums.Difficulty;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.enums.Dimension;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.enums.LevelType;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.entity.EntityRegistry;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.game.world.config.WorldConfig;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.storage.world.WorldDataSource;

import javax.annotation.Nullable;

public interface World {
    String getID();

    WorldConfig getWorldConfig();

    WorldGenerator getWorldGenerator();

    Dimension getDimension();

    Difficulty getDifficulty();

    LevelType getLevelType();

    RotatablePosition getFirstSpawnPosition();

    WorldDataSource getDataSource();

    EntityRegistry getEntityRegistry();

    ListenableFuture<Block> getBlockAt(int x, int y, int z);

    ListenableFuture<Block> getBlockAt(Position position);

    void setBlockAt(int x, int y, int z, Block block);

    void setBlockAt(Position position, Block block);

    ListenableFuture<Chunk> getChunk(ChunkCoords coords);

    @Nullable
    Chunk getLoadedChunk(ChunkCoords coords);

    ListenableFuture<Chunk> getChunkAt(Position position);

    @Nullable
    Chunk getLoadedChunkAt(Position position);

    void unloadRegions();

    void delete();

    void close();
}
