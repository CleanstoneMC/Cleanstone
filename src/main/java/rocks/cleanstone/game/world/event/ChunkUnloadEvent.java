package rocks.cleanstone.game.world.event;

import rocks.cleanstone.game.world.chunk.Chunk;

public class ChunkUnloadEvent {

    private final Chunk chunk;

    public ChunkUnloadEvent(Chunk chunk) {
        this.chunk = chunk;
    }

    public Chunk getChunk() {
        return chunk;
    }
}
