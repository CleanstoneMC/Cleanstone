package rocks.cleanstone.game;

import com.google.common.base.Objects;
import java.io.Serializable;
import rocks.cleanstone.utils.Vector;

/**
 * Mutable 3D position in the world
 */
public class Position implements Serializable {

    private static final long serialVersionUID = -978234L;

    protected double x, y, z;

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

    public double getX() {
        return x;
    }

    // floor it, because -0.5 needs to become -1
    public int getXAsInt() {
        return (int) Math.floor(getX());
    }

    public void setX(double x) {
        this.x = x;
    }

    public void addX(double x) {
        this.x += x;
    }

    public double getY() {
        return y;
    }

    public int getYAsInt() {
        return (int) Math.floor(getY());
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addY(double y) {
        this.y += y;
    }

    public double getZ() {
        return z;
    }

    public int getZAsInt() {
        return (int) Math.floor(getZ());
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void addZ(double z) {
        this.z += z;
    }

    public double getDistance(Position position) {
        double xP = Math.pow((this.getX() - position.getX()), 2);
        double yP = Math.pow((this.getY() - position.getY()), 2);
        double zP = Math.pow((this.getZ() - position.getZ()), 2);

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return equalCoordinates(position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y, z);
    }

    @Override
    public String toString() {
        return "X=" + getXAsInt() + ";Y=" + getYAsInt() + ";Z=" + getZAsInt();
    }
}
