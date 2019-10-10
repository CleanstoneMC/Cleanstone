package rocks.cleanstone.game.world.chunk;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.storage.chunk.BlockDataStorage;
import rocks.cleanstone.storage.engine.leveldb.entity.EntityData;

import java.util.Collection;

public interface Chunk {

    int WIDTH = 16, HEIGHT = 256;

    /**
     * @return An immutable collection of all non-Air blocks contained within this Chunk
     */
    Collection<Block> getBlocks();

    Collection<Entity> getEntities();

    ChunkCoords getCoordinates();

    Block getBlock(int x, int y, int z);

    void setBlock(int x, int y, int z, Block block);

    byte getBlockLight(int x, int y, int z);

    void setBlockLight(int x, int y, int z, byte blockLight);

    byte getSkyLight(int x, int y, int z);

    void setSkyLight(int x, int y, int z, byte skyLight);

    boolean hasSkylight();

    void setHasUnsavedChanges(boolean hasUnsavedChanges);

    boolean hasUnsavedChanges();

    BlockDataStorage getBlockDataStorage();

    EntityData getEntityData();
}
