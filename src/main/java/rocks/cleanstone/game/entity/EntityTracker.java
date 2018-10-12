package rocks.cleanstone.game.entity;

import java.util.Collection;

import rocks.cleanstone.game.entity.event.EntityAddEvent;
import rocks.cleanstone.game.entity.event.EntityMoveEvent;
import rocks.cleanstone.game.entity.event.EntityRemoveEvent;
import rocks.cleanstone.player.Player;

public interface EntityTracker {
    void addObserver(Entity observer);

    void removeObserver(Entity observer);

    <T extends Entity> Collection<T> getTrackedEntities(Entity observer, Class<T> entityClass);

    <T extends Entity> Collection<T> getObservers(Entity entity, Class<T> entityClass);

    Collection<Player> getPlayerObservers(Entity trackedEntity);

    Collection<Player> getTrackedPlayers(Entity observer);

    boolean isTracking(Entity observer, Entity entity);

    boolean isObserver(Entity entity);

    void onEntityMove(EntityMoveEvent e);

    void onEntityAdd(EntityAddEvent event);

    void onEntityRemove(EntityRemoveEvent event);
}
