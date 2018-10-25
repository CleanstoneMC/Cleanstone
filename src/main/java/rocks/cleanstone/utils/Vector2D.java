package rocks.cleanstone.utils;

import java.util.Objects;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Wither
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

    public Vector2D addVector(Vector2D vector) {
        return new Vector2D(
                x + vector.x,
                y + vector.y
        );
    }

    public Vector2D subtractVector(Vector2D vector) {
        return new Vector2D(
                x - vector.x,
                y - vector.y
        );
    }

    public Vector2D multiplyVector(Vector2D vector) {
        return new Vector2D(
                x * vector.x,
                y * vector.y
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector2D)) {
            return false;
        }
        final Vector2D vector = (Vector2D) obj;
        return !(vector.x != x) && !(vector.y != y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
