package rocks.cleanstone.game.world.region.chunk;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.entity.Entity;

import java.util.Collection;

public interface Chunk {

    int WIDTH = 16, HEIGHT = 256;

    /**
     * @return An immutable collection of all non-Air blocks contained within this Chunk
     */
    Collection<Block> getBlocks();

    Collection<Entity> getEntities();

    int getX();

    int getY();

    Block getBlock(int x, int y, int z);

    byte getBlockLight(int x, int y, int z);

    byte getSkyLight(int x, int y, int z);

    boolean hasSkylight();
}
