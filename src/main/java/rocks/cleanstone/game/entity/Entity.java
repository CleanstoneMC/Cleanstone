package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.world.World;

public interface Entity {

    EntityType getType();

    int getEntityID();

    RotatablePosition getPosition();

    void setPosition(RotatablePosition rotatablePosition);

    World getWorld();
}
