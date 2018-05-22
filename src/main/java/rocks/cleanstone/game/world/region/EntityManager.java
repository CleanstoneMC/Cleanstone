package rocks.cleanstone.game.world.region;

import rocks.cleanstone.game.entity.AbstractEntity;
import rocks.cleanstone.game.entity.Entity;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

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

    public Entity addEntityWithoutID(Entity entity) {
        if (!(entity instanceof AbstractEntity)) {
            return null;
        }

        ((AbstractEntity) entity).setEntityID(getUnoccupiedEntityID());

        entityMap.put(entity.getEntityID(), entity);

        return entity;
    }

    private Integer getUnoccupiedEntityID() {
        ThreadLocalRandom current = ThreadLocalRandom.current();

        int random = current.nextInt();
        while (entityMap.containsKey(random)) {
            random = current.nextInt();
        }

        return random;
    }
}
