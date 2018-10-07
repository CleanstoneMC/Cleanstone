package rocks.cleanstone.game.world.chunk;

import java.util.Objects;

import rocks.cleanstone.game.Position;

public class ChunkCoords {

    private final int x, z;

    private ChunkCoords(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public static ChunkCoords of(Position blockPosition) {
        return of(blockPosition.getXAsInt() >> 4, blockPosition.getZAsInt() >> 4);
    }

    public static ChunkCoords of(int x, int z) {
        return new ChunkCoords(x, z);
    }

    public static ChunkCoords ofBlockCoords(int blockX, int blockZ) {
        return of(blockX >> 4, blockZ >> 4);
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public int distance(ChunkCoords other) {
        double xP = Math.pow((getX() - other.getX()), 2);
        double zP = Math.pow((getZ() - other.getZ()), 2);
        return (int) Math.ceil(Math.sqrt(xP + zP));
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
