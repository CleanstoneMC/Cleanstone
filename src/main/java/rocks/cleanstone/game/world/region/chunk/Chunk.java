package rocks.cleanstone.game.world.region.chunk;

import java.util.Collection;

import rocks.cleanstone.game.world.region.block.Block;

public interface Chunk {
    Collection<Block> getBlocks();

    int getSize();

    int getX();

    int getY();
}
