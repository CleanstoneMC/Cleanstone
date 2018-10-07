package rocks.cleanstone.game.entity.event;

import rocks.cleanstone.game.entity.Entity;

public class EntityUntrackEvent {

    private final Entity observer, entity;

    public EntityUntrackEvent(Entity observer, Entity entity) {
        this.observer = observer;
        this.entity = entity;
    }

    public Entity getObserver() {
        return observer;
    }

    public Entity getEntity() {
        return entity;
    }
}
