package rocks.cleanstone.game.world;

import org.springframework.util.concurrent.ListenableFuture;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.net.packet.enums.Difficulty;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;

public interface World {
    String getID();

    Dimension getDimension();

    Difficulty getDifficulty();

    LevelType getLevelType();

    RotatablePosition getFirstSpawnPosition();

    WorldDataSource getDataSource();

    ListenableFuture<Block> getBlockAt(int x, int y, int z);

    ListenableFuture<Block> getBlockAt(Position position);

    void setBlockAt(int x, int y, int z, Block block);

    void setBlockAt(Position position, Block block);

    ListenableFuture<Chunk> getChunk(int chunkX, int chunkY);

    ListenableFuture<Chunk> getChunkAt(Position position);

    void close();
}
