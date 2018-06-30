package rocks.cleanstone.game.world;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.world.chunk.ChunkProvider;
import rocks.cleanstone.net.packet.enums.Difficulty;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;
import rocks.cleanstone.utils.Vector;

public interface World {
    String getID();

    Dimension getDimension();

    Difficulty getDifficulty();

    LevelType getLevelType();

    Location getFirstSpawnLocation();

    ChunkProvider getChunkProvider();

    Block getBlockAt(int x, int y, int z);

    Block getBlockAt(Vector vector);

    void setBlockAt(int x, int y, int z, Block block);

    void setBlockAt(Vector vector, Block block);
}
