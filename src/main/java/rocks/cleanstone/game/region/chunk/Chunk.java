package rocks.cleanstone.game.region.chunk;

import rocks.cleanstone.game.region.block.Block;

import java.util.Collection;

public interface Chunk {
    Collection<Block> getBlocks();

    int getSize();

    int getX();

    int getY();
}
