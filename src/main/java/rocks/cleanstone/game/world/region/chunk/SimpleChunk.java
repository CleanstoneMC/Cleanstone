package rocks.cleanstone.game.world.region.chunk;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.world.region.block.Block;

import java.util.Collection;
import java.util.HashSet;

public class SimpleChunk implements Chunk {

    private final Collection<Entity> entityCollection;
    private final ChunkTable chunkTable;

    public SimpleChunk(Collection<Entity> entityCollection, ChunkTable chunkTable) {
        this.entityCollection = entityCollection;
        this.chunkTable = chunkTable;
    }

    public SimpleChunk(Collection<Entity> entityCollection) {
        this.entityCollection = entityCollection;
        chunkTable = new ChunkTable();
    }

    public SimpleChunk(ChunkTable chunkTable) {
        this.chunkTable = chunkTable;
        entityCollection = new HashSet<>();
    }

    public SimpleChunk() {
        entityCollection = new HashSet<>();
        chunkTable = new ChunkTable();
    }

    @Override
    public Collection<Block> getBlocks() {
        return chunkTable.getBlocks();
    }

    @Override
    public Collection<Entity> getEntities() {
        return new HashSet<>();
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }
}
