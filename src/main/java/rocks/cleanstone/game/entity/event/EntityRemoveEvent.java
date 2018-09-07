package rocks.cleanstone.game.entity.event;

import rocks.cleanstone.game.entity.Entity;

public class EntityRemoveEvent {

    private final Entity entity;

    public EntityRemoveEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
