package rocks.cleanstone.game.world.region.chunk;

import java.util.Collection;

import javax.annotation.Nullable;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.world.region.block.Block;

public class SimpleChunk implements Chunk {

    private final Collection<Entity> entityCollection;
    private final ChunkTable chunkTable;
    private final int x;
    private final int y;

    public SimpleChunk(Collection<Entity> entityCollection, ChunkTable chunkTable, int x, int y) {
        this.entityCollection = entityCollection;
        this.chunkTable = chunkTable;
        this.x = x;
        this.y = y;
    }

    @Override
    public Collection<Block> getBlocks() {
        return chunkTable.getBlocks();
    }

    @Override
    public Collection<Entity> getEntities() {
        return entityCollection;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Nullable
    @Override
    public Block getBlock(int x, int y, int z) {
        return chunkTable.getBlock(x, y, z);
    }

    @Override
    public byte getBlockLight(int x, int y, int z) {
        // TODO
        return 0;
    }

    @Override
    public byte getSkyLight(int x, int y, int z) {
        // TODO
        return 0;
    }

    @Override
    public boolean hasSkylight() {
        // TODO
        return false;
    }
}
