package rocks.cleanstone.game.entity;

import com.google.common.base.Preconditions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.entity.event.EntityAddEvent;
import rocks.cleanstone.game.entity.event.EntityRemoveEvent;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class SimpleEntityRegistry implements EntityRegistry {
    private final Map<Integer, Entity> entityMap = new HashMap<>();
    private int nextEntityID = 0;

    public SimpleEntityRegistry(Map<Integer, Entity> entityMap) {
        this.entityMap.putAll(entityMap);
    }

    public SimpleEntityRegistry() {
    }

    @Override
    public void addEntity(Entity entity) {
        entityMap.put(entity.getEntityID(), entity);
        CleanstoneServer.publishEvent(new EntityAddEvent(entity));
    }

    @Nullable
    @Override
    public Entity getEntityByID(int entityID) {
        return entityMap.get(entityID);
    }

    @Override
    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getEntityID());
        CleanstoneServer.publishEvent(new EntityRemoveEvent(entity));
    }

    @Override
    public int acquireEntityID() {
        return nextEntityID++;
    }
}
