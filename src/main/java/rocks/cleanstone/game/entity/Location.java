package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.Position;

public class Location {
    private Position position;
    private Rotation rotation;

    public Location(Position position, Rotation rotation) {
        this.position = position;
        this.rotation = rotation;
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
}
