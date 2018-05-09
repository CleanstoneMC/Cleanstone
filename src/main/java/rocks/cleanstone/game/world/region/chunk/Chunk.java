package rocks.cleanstone.game.world.region.chunk;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.world.region.block.Block;

import java.util.Collection;

public interface Chunk {

    Collection<Block> getBlocks();

    Collection<Entity> getEntities();

    int getX();

    int getY();

    Block getBlock(int x, int y, int z);
}
