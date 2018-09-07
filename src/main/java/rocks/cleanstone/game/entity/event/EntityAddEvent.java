package rocks.cleanstone.game.entity.event;

import rocks.cleanstone.game.entity.Entity;

public class EntityAddEvent {

    private final Entity entity;

    public EntityAddEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
