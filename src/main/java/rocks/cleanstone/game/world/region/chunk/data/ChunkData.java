package rocks.cleanstone.game.world.region.chunk.data;

import rocks.cleanstone.game.world.data.WorldData;

public abstract class ChunkData implements WorldData {

    private final int x, y;

    public ChunkData(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getChunkX() {
        return x;
    }

    public int getChunkY() {
        return y;
    }
}
