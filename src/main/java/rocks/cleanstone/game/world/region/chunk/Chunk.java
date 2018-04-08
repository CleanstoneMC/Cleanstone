package rocks.cleanstone.game.world.region.chunk;

import rocks.cleanstone.game.world.region.block.Block;

import java.util.Collection;

public interface Chunk {
    Collection<Block> getBlocks();

    int getSize();

    int getX();

    int getY();
}
