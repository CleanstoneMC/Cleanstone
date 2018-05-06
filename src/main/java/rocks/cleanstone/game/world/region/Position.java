package rocks.cleanstone.game.world.region;

import javafx.geometry.Pos;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.net.utils.Vector;

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
    public Position clone() {
        return new Position(x, y, z, world);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }

        Position position = (Position) obj;

        if (position.x != x || position.y != y || position.z != z || position.world != world) {
            return false;
        }

        return true;
    }
}
