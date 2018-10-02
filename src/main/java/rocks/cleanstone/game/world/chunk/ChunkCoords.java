package rocks.cleanstone.game.world.chunk;

import java.util.Objects;

public class ChunkCoords {

    private final int x, z;

    private ChunkCoords(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public static ChunkCoords of(Chunk chunk) {
        return of(chunk.getX(), chunk.getZ());
    }

    public static ChunkCoords of(int x, int z) {
        return new ChunkCoords(x, z);
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChunkCoords that = (ChunkCoords) o;
        return x == that.x && z == that.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }

    @Override
    public String toString() {
        return x + ":" + z;
    }
}
