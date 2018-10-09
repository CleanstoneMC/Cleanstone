package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.world.World;

public abstract class AbstractEntity implements Entity {

    private final EntityType type;
    private final World world;
    private final int entityID;
    private boolean persistent;
    protected RotatablePosition position;

    protected AbstractEntity(EntityType type, World world, RotatablePosition position, boolean persistent) {
        this.type = type;
        this.world = world;
        this.position = position;
        this.persistent = persistent;

        entityID = world.getEntityRegistry().acquireEntityID();
    }

    @Override
    public EntityType getType() {
        return type;
    }

    @Override
    public int getEntityID() {
        return entityID;
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
    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" + "entityID=" + entityID + '}';
    }
}
