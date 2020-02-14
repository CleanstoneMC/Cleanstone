package rocks.cleanstone.logic.entity.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.world.event.ChunkLoadedEvent;
import rocks.cleanstone.game.world.event.ChunkUnloadEvent;

@Component
public class EntityRegistrationCauseListener {

    @EventListener
    public void onChunkLoaded(ChunkLoadedEvent e) {
        e.getChunk().getEntities().forEach(entity -> entity.getWorld().getEntityRegistry().addEntity(entity));
    }

    @EventListener
    public void onChunkUnload(ChunkUnloadEvent e) {
        e.getChunk().getEntities().forEach(entity -> entity.getWorld().getEntityRegistry().removeEntity(entity));
    }
}
