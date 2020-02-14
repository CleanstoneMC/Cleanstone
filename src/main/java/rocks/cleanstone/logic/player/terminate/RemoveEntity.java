package rocks.cleanstone.logic.player.terminate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.EntityTracker;
import rocks.cleanstone.player.event.AsyncPlayerTerminationEvent;

@Component
public class RemoveEntity {

    private final EntityTracker entityTracker;

    @Autowired
    public RemoveEntity(EntityTracker entityTracker) {
        this.entityTracker = entityTracker;
    }

    @Order(value = 100)
    @EventListener
    public void onTerminate(AsyncPlayerTerminationEvent e) {
        Entity playerEntity = e.getPlayer().getEntity();
        if (playerEntity == null) return;
        entityTracker.removeObserver(playerEntity);
        playerEntity.getWorld().getEntityRegistry().removeEntity(playerEntity);
    }
}
