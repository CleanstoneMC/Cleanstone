package rocks.cleanstone.utils;

import java.util.Objects;

public class Vector2D {

    protected double x;
    protected double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Vector2D() {
    }

    public void addVector(Vector2D vector) {
        this.x += vector.getX();
        this.y += vector.getY();
    }

    public void subtractVector(Vector2D vector) {
        this.x -= vector.getX();
        this.y -= vector.getY();
    }

    public void multiplyVector(Vector2D vector) {
        this.x *= vector.getX();
        this.y *= vector.getY();
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector2D)) {
            return false;
        }
        Vector2D vector = (Vector2D) obj;
        return !(vector.x != x) && !(vector.y != y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
