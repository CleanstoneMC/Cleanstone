package rocks.cleanstone.utils;

import com.google.common.base.Objects;

/**
 * Mutable 3D Vector in the world
 */
public class Vector {

    protected double x;
    protected double y;
    protected double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Vector other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public Vector() {
    }

    public Vector addVector(Vector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
        this.z += vector.getZ();
        return this;
    }

    public Vector subtractVector(Vector vector) {
        this.x -= vector.getX();
        this.y -= vector.getY();
        this.z -= vector.getZ();
        return this;
    }

    public Vector multiplyVector(Vector vector) {
        this.x *= vector.getX();
        this.y *= vector.getY();
        this.z *= vector.getZ();
        return this;
    }

    public double getX() {
        return x;
    }

    public long getXAsLong() {
        return (long) Math.floor(getX());
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

    public long getYAsLong() {
        return (long) Math.floor(getY());
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

    public long getZAsLong() {
        return (long) Math.floor(getZ());
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void addZ(double z) {
        this.z += z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return Double.compare(vector.x, x) == 0 &&
                Double.compare(vector.y, y) == 0 &&
                Double.compare(vector.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y, z);
    }
}
