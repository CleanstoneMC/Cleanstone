package rocks.cleanstone.region.chunk;

import rocks.cleanstone.region.block.Block;

import java.util.Collection;

public interface Chunk {
    Collection<Block> getBlocks();

    int getSize();

    int getX();

    int getY();
}
