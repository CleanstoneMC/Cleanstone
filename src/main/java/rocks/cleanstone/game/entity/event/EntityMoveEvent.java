package rocks.cleanstone.game.entity.event;

import rocks.cleanstone.core.event.CancellableEvent;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.HeadRotatablePosition;

public class EntityMoveEvent extends CancellableEvent {

    private final Entity entity;
    private final HeadRotatablePosition oldPosition;
    private HeadRotatablePosition newPosition;

    public EntityMoveEvent(Entity entity, HeadRotatablePosition oldPosition,
                           HeadRotatablePosition newPosition) {
        this.entity = entity;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    public Entity getEntity() {
        return entity;
    }

    public HeadRotatablePosition getOldPosition() {
        return oldPosition;
    }

    public HeadRotatablePosition getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(HeadRotatablePosition newPosition) {
        this.newPosition = newPosition;
    }
}
