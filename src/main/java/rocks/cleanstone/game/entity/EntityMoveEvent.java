package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.Position;

public class EntityMoveEvent {
    private final Entity entity;
    private final Position oldPosition;
    private final Rotation oldRotation;
    private Position newPosition;
    private Rotation newRotation;

    public EntityMoveEvent(Entity entity, Position oldPosition, Rotation oldRotation,
                           Position newPosition, Rotation newRotation) {
        this.entity = entity;
        this.oldPosition = oldPosition;
        this.oldRotation = oldRotation;
        this.newPosition = newPosition;
        this.newRotation = newRotation;
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

    public Rotation getOldRotation() {
        return oldRotation;
    }

    public Rotation getNewRotation() {
        return newRotation;
    }

    public void setNewRotation(Rotation newRotation) {
        this.newRotation = newRotation;
    }
}
