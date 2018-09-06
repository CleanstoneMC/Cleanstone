package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.world.World;

public abstract class AbstractEntity implements Entity {

    private final EntityType type;
    private final World world;
    protected RotatablePosition position;
    private boolean persistent;
    private int entityID;

    protected AbstractEntity(int entityID, EntityType type, World world, RotatablePosition position,
                             boolean persistent) {
        this.entityID = entityID;
        this.type = type;
        this.world = world;
        this.position = position;
        this.persistent = persistent;
    }

    protected AbstractEntity(EntityType type, World world, RotatablePosition position, boolean persistent) {
        this.type = type;
        this.position = position;
        this.world = world;
    }

    public EntityType getType() {
        return type;
    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
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
    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }
}
