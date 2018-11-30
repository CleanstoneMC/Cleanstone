package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.world.World;

public interface Entity {

    int getEntityID();

    void setEntityID(int id);

    RotatablePosition getPosition();

    void setPosition(RotatablePosition rotatablePosition);

    World getWorld();

    boolean isPersistent();

    /**
     * @return TRUE if this entity should be able to be spawned and used in the game
     */
    boolean isSpawnable();

    boolean isGlowing();

    void setGlowing(boolean glowing);
}
