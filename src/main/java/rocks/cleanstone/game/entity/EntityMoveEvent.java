package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.Position;

public class EntityMoveEvent {
    private final Entity entity;
    private final Position oldPosition;
    private Position newPosition;

    public EntityMoveEvent(Entity entity, Position oldPosition, Position newPosition) {
        this.entity = entity;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    public Entity getEntity() {
        return entity;
    }

    public Position getOldPosition() {
        return oldPosition;
    }

    public Position getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(Position newPosition) {
        this.newPosition = newPosition;
    }
}
