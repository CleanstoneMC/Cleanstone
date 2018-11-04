package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.entity.metadata.Metadata;
import rocks.cleanstone.game.world.World;

public interface Entity {

    EntityType getType();

    int getEntityID();

    RotatablePosition getPosition();

    void setPosition(RotatablePosition rotatablePosition);

    World getWorld();

    boolean isPersistent();

    void setPersistent(boolean persistent);

    Metadata getMetadata();
}
