package rocks.cleanstone.game.world.region;

import rocks.cleanstone.game.entity.Entity;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class EntityManager {
    private final Map<Integer, Entity> entityMap = new HashMap<>();

    public EntityManager(Map<Integer, Entity> entityMap) {
        this.entityMap.putAll(entityMap);
    }

    public EntityManager() {
    }

    @Nullable
    public Entity getEntityByID(int entityID) {
        return entityMap.get(entityID);
    }
}
