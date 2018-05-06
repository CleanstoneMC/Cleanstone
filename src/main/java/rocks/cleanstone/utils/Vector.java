package rocks.cleanstone.utils;

import java.util.Objects;

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

    public void addVector(Vector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
        this.z += vector.getZ();
    }

    public void subtractVector(Vector vector) {
        this.x -= vector.getX();
        this.y -= vector.getY();
        this.z -= vector.getZ();
    }

    public void mulitplyVector(Vector vector) {
        this.x *= vector.getX();
        this.y *= vector.getY();
        this.z *= vector.getZ();
    }

    public double getX() {
        return x;
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

    public void setY(double y) {
        this.y = y;
    }

    public void addY(double y) {
        this.y += y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void addZ(double z) {
        this.z += z;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector)) {
            return false;
        }
        Vector vector = (Vector) obj;
        return !(vector.x != x) && !(vector.y != y) && !(vector.z != z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
