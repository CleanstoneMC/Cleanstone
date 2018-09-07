package rocks.cleanstone.game.world.event;

import rocks.cleanstone.game.world.chunk.Chunk;

public class ChunkLoadedEvent {

    private final Chunk chunk;

    public ChunkLoadedEvent(Chunk chunk) {
        this.chunk = chunk;
    }

    public Chunk getChunk() {
        return chunk;
    }
}
