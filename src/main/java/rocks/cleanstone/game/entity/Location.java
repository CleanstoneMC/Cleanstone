package rocks.cleanstone.game.entity;

import com.google.common.base.Objects;

import rocks.cleanstone.game.Position;

public class Location {
    private Position position;
    private Rotation rotation;

    public Location(Position position, Rotation rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Location(Location location) {
        this.position = new Position(location.getPosition());
        this.rotation = new Rotation(location.getRotation());
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equal(position, location.position) &&
                Objects.equal(rotation, location.rotation);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position, rotation);
    }
}
