package rocks.cleanstone.game;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Wither;
import rocks.cleanstone.utils.Vector;

@Getter
@Wither
public class Position implements Serializable {
    private static final long serialVersionUID = -978234L;

    protected final double x, y, z;

    public Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position(Position position) {
        this(position.x, position.y, position.z);
    }

    public Position(Vector vector) {
        this(vector.getX(), vector.getY(), vector.getZ());
    }

    // floor it, because -0.5 needs to become -1
    public int getXAsInt() {
        return (int) Math.floor(getX());
    }

    public int getYAsInt() {
        return (int) Math.floor(getY());
    }

    public int getZAsInt() {
        return (int) Math.floor(getZ());
    }

    public double getDistance(Position position) {
        final double xP = Math.pow((this.getX() - position.getX()), 2);
        final double yP = Math.pow((this.getY() - position.getY()), 2);
        final double zP = Math.pow((this.getZ() - position.getZ()), 2);

        return Math.sqrt(xP + yP + zP);
    }

    public Vector toVector() {
        return new Vector(x, y, z);
    }

    public boolean equalCoordinates(Position position) {
        return Double.compare(position.x, x) == 0 &&
                Double.compare(position.y, y) == 0 &&
                Double.compare(position.z, z) == 0;
    }
}
