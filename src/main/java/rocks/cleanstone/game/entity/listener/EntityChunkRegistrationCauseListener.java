package rocks.cleanstone.game.entity.listener;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.event.EntityAddEvent;
import rocks.cleanstone.game.entity.event.EntityMoveEvent;
import rocks.cleanstone.game.entity.event.EntityRemoveEvent;
import rocks.cleanstone.game.world.chunk.Chunk;

@Slf4j
@Component
public class EntityChunkRegistrationCauseListener {
    @Async
    @EventListener
    public void onEntityMove(EntityMoveEvent e) throws Exception {
        Entity entity = e.getEntity();
        Chunk oldChunk = entity.getWorld().getChunkAt(e.getOldPosition()).get();
        Chunk newChunk = entity.getWorld().getChunkAt(e.getNewPosition()).get();
        Preconditions.checkNotNull(oldChunk);
        Preconditions.checkNotNull(newChunk);
        if (oldChunk != newChunk) {
            oldChunk.getEntities().remove(entity);
            newChunk.getEntities().add(entity);
        }
    }

    @EventListener
    public void onEntityAdd(EntityAddEvent e) {
        Entity entity = e.getEntity();
        entity.getWorld().getChunkAt(entity.getPosition()).addCallback(chunk -> {
            Preconditions.checkNotNull(chunk);
            chunk.getEntities().add(entity);
        }, Throwable::printStackTrace);
    }

    @EventListener
    public void onEntityRemove(EntityRemoveEvent e) {
        Entity entity = e.getEntity();
        entity.getWorld().getChunkAt(entity.getPosition()).addCallback(chunk -> {
            Preconditions.checkNotNull(chunk);
            chunk.getEntities().remove(entity);
        }, Throwable::printStackTrace);
    }
}
