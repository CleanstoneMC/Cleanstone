package rocks.cleanstone.player.terminate;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.player.event.AsyncPlayerTerminationEvent;

@Component
public class RemoveEntity {

    @Order(value = 100)
    @EventListener
    public void onTerminate(AsyncPlayerTerminationEvent e) {
        Entity playerEntity = e.getPlayer().getEntity();
        playerEntity.getWorld().getEntityRegistry().removeEntity(playerEntity);
    }
}
