package rocks.cleanstone.game.world;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.world.region.chunk.ChunkProvider;
import rocks.cleanstone.net.packet.enums.Difficulty;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;

import javax.annotation.Nullable;

public interface World {
    String getID();

    Dimension getDimension();

    Difficulty getDifficulty();

    LevelType getLevelType();

    Location getFirstSpawnLocation();

    ChunkProvider getChunkProvider();

    @Nullable
    Block getBlockAt(int x, int y, int z);
}
