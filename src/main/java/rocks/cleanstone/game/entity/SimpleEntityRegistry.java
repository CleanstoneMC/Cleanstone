package rocks.cleanstone.game.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nullable;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.entity.event.EntityAddEvent;
import rocks.cleanstone.game.entity.event.EntityRemoveEvent;

@Component
@Scope("prototype")
public class SimpleEntityRegistry implements EntityRegistry {

    private final Map<Integer, Entity> entityMap = new ConcurrentHashMap<>();
    private int nextEntityID = 0;

    public SimpleEntityRegistry(Map<Integer, Entity> entityMap) {
        this.entityMap.putAll(entityMap);
    }

    public SimpleEntityRegistry() {
    }

    @Override
    public void addEntity(Entity entity) {
        // TODO only allow top-level entities (not LivingEntity,etc) to be added
        int entityID = acquireEntityID();
        entity.setEntityID(entityID);
        entityMap.put(entityID, entity);
        CleanstoneServer.publishEvent(new EntityAddEvent(entity));
    }

    @Nullable
    @Override
    public Entity getEntityByID(int entityID) {
        return entityMap.get(entityID);
    }

    @Override
    public void removeEntity(Entity entity) {
        CleanstoneServer.publishEvent(new EntityRemoveEvent(entity));
        entityMap.remove(entity.getEntityID());
    }

    private synchronized int acquireEntityID() {
        return nextEntityID++;
    }
}
