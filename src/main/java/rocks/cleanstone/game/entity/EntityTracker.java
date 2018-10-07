package rocks.cleanstone.game.entity;

import java.util.Collection;

import rocks.cleanstone.player.Player;

public interface EntityTracker {
    void addObserver(Entity observer);

    void removeObserver(Entity observer);

    <T extends Entity> Collection<T> getTrackedEntities(Entity observer, Class<T> entityClass);

    Collection<Player> getTrackedPlayers(Entity observer);

    boolean isTracking(Entity observer, Entity entity);
}
