package rocks.cleanstone.game;

import rocks.cleanstone.game.world.World;
import rocks.cleanstone.utils.Vector;

import java.util.Objects;

public class Position extends Vector {

    private World world;

    public Position(double x, double y, double z, World world) {
        super(x, y, z);
        this.world = world;
    }

    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
        this.z = position.z;
        this.world = position.world;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }
        Position position = (Position) obj;
        return !(position.x != x) && !(position.y != y) && !(position.z != z) && position.world == world;
    }

    @Override
    public int hashCode() {
        return Objects.hash(world, x, y, z);
    }
}
