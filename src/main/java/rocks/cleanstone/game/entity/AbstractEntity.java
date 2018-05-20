package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.Position;

public abstract class AbstractEntity implements Entity {

    private final int entityID;

    private final EntityType type;

    private Position position;

    protected AbstractEntity(int entityID, EntityType type, Position position) {
        this.entityID = entityID;
        this.type = type;
        this.position = position;
    }

    public EntityType getType() {
        return type;
    }

    public int getEntityID() {
        return entityID;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
