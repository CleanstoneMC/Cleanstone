package rocks.cleanstone.game.world;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.world.chunk.ChunkProvider;
import rocks.cleanstone.net.packet.enums.Difficulty;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;

public interface World {
    String getID();

    Dimension getDimension();

    Difficulty getDifficulty();

    LevelType getLevelType();

    RotatablePosition getFirstSpawnPosition();

    ChunkProvider getChunkProvider();

    Block getBlockAt(int x, int y, int z);

    Block getBlockAt(Position position);

    void setBlockAt(int x, int y, int z, Block block);

    void setBlockAt(Position position, Block block);
}
