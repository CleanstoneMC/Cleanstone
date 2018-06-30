package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.world.World;

public abstract class AbstractEntity implements Entity {

    private final EntityType type;
    private final World world;
    protected RotatablePosition position;
    private int entityID;

    protected AbstractEntity(int entityID, EntityType type, World world, RotatablePosition position) {
        this.entityID = entityID;
        this.type = type;
        this.world = world;
        this.position = position;
    }

    protected AbstractEntity(EntityType type, World world, RotatablePosition position) {
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
}
