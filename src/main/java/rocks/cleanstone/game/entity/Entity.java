package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.Position;

public interface Entity {

    EntityType getType();

    int getEntityID();

    Position getPosition();

    void setPosition(Position position);

    Rotation getRotation();

    void setRotation(Rotation rotation);
}
