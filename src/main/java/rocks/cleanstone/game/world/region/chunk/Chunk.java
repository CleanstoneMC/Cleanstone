package rocks.cleanstone.game.world.region.chunk;

import java.util.Collection;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.world.region.block.Block;

public interface Chunk {

    int WIDTH = 16, HEIGHT = 256;

    Collection<Block> getBlocks();

    Collection<Entity> getEntities();

    int getX();

    int getY();

    Block getBlock(int x, int y, int z);

    byte getBlockLight(int x, int y, int z);

    byte getSkyLight(int x, int y, int z);

    boolean hasSkylight();
}
