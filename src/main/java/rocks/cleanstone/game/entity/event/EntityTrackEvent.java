package rocks.cleanstone.game.entity.event;

import rocks.cleanstone.game.entity.Entity;

public class EntityTrackEvent {

    private final Entity observer, entity;

    public EntityTrackEvent(Entity observer, Entity entity) {
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
