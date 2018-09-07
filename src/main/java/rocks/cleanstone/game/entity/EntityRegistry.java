package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.entity.Entity;

import javax.annotation.Nullable;

public interface EntityRegistry {
    void addEntity(Entity entity);

    @Nullable
    Entity getEntityByID(int entityID);

    void removeEntity(Entity entity);

    int acquireEntityID();
}
