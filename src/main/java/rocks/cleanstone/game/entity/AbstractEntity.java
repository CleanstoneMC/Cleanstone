package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.Position;

public abstract class AbstractEntity implements Entity {

    private int entityID;

    private final EntityType type;

    private Position position;

    private Rotation rotation;

    protected AbstractEntity(int entityID, EntityType type, Position position, Rotation rotation) {
        this.entityID = entityID;
        this.type = type;
        this.position = position;
        this.rotation = rotation;
    }

    protected AbstractEntity(EntityType type, Position position, Rotation rotation) {
        this.type = type;
        this.position = position;
        this.rotation = rotation;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }
}
