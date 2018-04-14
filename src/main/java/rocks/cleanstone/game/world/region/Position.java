package rocks.cleanstone.game.world.region;

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
}
