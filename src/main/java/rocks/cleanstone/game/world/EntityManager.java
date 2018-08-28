package rocks.cleanstone.game.world;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.entity.AbstractEntity;
import rocks.cleanstone.game.entity.Entity;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component("entityManager")
public class EntityManager {
    private final Map<Integer, Entity> entityMap = new HashMap<>();
    private final ThreadLocalRandom current = ThreadLocalRandom.current();

    public EntityManager(Map<Integer, Entity> entityMap) {
        this.entityMap.putAll(entityMap);
    }

    public EntityManager() {
    }

    @Nullable
    public Entity getEntityByID(int entityID) {
        return entityMap.get(entityID);
    }

    public <T extends Entity> T addEntityWithoutID(T entity) {
        if (!(entity instanceof AbstractEntity)) {
            return null;
        }

        ((AbstractEntity) entity).setEntityID(getUnoccupiedEntityID());

        entityMap.put(entity.getEntityID(), entity);

        return entity;
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getEntityID());
    }

    public void removeEntity(int entityID) {
        entityMap.remove(entityID);
    }

    private int getUnoccupiedEntityID() {
        int random = current.nextInt();

        while (entityMap.containsKey(random)) {
            random = current.nextInt();
        }

        return random;
    }
}
