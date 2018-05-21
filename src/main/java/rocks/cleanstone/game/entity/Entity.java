package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.Position;

public interface Entity {

    EntityType getType();

    int getEntityID();

    Position getPosition();

    Rotation getRotation();
}
