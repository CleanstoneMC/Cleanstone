package rocks.cleanstone.game.world.event;

import rocks.cleanstone.game.world.chunk.Chunk;

public class ChunkSaveEvent {

    private final Chunk chunk;

    public ChunkSaveEvent(Chunk chunk) {
        this.chunk = chunk;
    }

    public Chunk getChunk() {
        return chunk;
    }
}
