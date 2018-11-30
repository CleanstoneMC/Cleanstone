package rocks.cleanstone.game.entity;

import com.google.common.base.Preconditions;

import rocks.cleanstone.game.world.World;

public class SimpleEntity implements Entity {

    private final World world;
    private final boolean persistent, spawnable;
    protected RotatablePosition position;
    private EntityType type;
    private int entityID = -1;
    private boolean glowing;

    public SimpleEntity(World world, RotatablePosition position, boolean persistent, boolean spawnable,
                        boolean glowing) {
        this.world = world;
        this.position = position;
        this.persistent = persistent;
        this.spawnable = spawnable;
        this.glowing = glowing;
    }

    @Override
    public int getEntityID() {
        return entityID;
    }

    @Override
    public void setEntityID(int entityID) {
        Preconditions.checkState(this.entityID == -1, "Cannot reassign entityID");
        Preconditions.checkState(entityID > -1, "Invalid entityID " + entityID);
        this.entityID = entityID;
    }

    @Override
    public RotatablePosition getPosition() {
        return position;
    }

    @Override
    public void setPosition(RotatablePosition rotatablePosition) {
        this.position = rotatablePosition;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public boolean isPersistent() {
        return persistent;
    }

    @Override
    public boolean isSpawnable() {
        return spawnable;
    }

    @Override
    public boolean isGlowing() {
        return glowing;
    }

    @Override
    public void setGlowing(boolean glowing) {
        this.glowing = glowing;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" + "entityID=" + entityID + '}';
    }
}
