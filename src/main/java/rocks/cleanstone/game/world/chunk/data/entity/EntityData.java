package rocks.cleanstone.game.world.chunk.data.entity;

import rocks.cleanstone.game.entity.Entity;

import java.util.Collection;

public class EntityData {
    private final Collection<Entity> entities;

    public EntityData(Collection<Entity> entities) {
        this.entities = entities;
    }

    public Collection<Entity> getEntities() {
        return entities;
    }
}
