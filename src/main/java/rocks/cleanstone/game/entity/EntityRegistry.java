package rocks.cleanstone.game.entity;

import javax.annotation.Nullable;

public interface EntityRegistry {

    void addEntity(Entity entity);

    @Nullable
    Entity getEntityByID(int entityID);

    void removeEntity(Entity entity);

}
