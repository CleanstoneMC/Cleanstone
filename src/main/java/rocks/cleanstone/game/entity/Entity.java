package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.world.World;

public interface Entity {

    int getEntityID();

    void setEntityID(int id);

    RotatablePosition getPosition();

    void setPosition(RotatablePosition rotatablePosition);

    World getWorld();

    boolean isPersistent();

    void setPersistent(boolean persistent);

    boolean isGlowing();

    void setGlowing(boolean glowing);
}
