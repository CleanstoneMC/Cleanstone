package rocks.cleanstone.game.entity;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.entity.event.EntityMoveEvent;
import rocks.cleanstone.game.entity.event.EntityTrackEvent;
import rocks.cleanstone.game.entity.event.EntityUntrackEvent;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

@Component
public class SimpleEntityTracker implements EntityTracker {

    private final NearbyEntityRetriever nearbyEntityRetriever;
    private final Multimap<Entity, Entity> observerTrackedEntitiesMap = Multimaps.synchronizedMultimap(HashMultimap.create());
    private final PlayerManager playerManager;
    private final int maxTrackingDistance = 10;

    @Autowired
    public SimpleEntityTracker(NearbyEntityRetriever nearbyEntityRetriever, PlayerManager playerManager) {
        this.nearbyEntityRetriever = nearbyEntityRetriever;
        this.playerManager = playerManager;
    }

    @Override
    public void addObserver(Entity observer) {
        observerTrackedEntitiesMap.putAll(observer, getInRangeEntities(observer));
    }

    @Override
    public void removeObserver(Entity observer) {
        observerTrackedEntitiesMap.removeAll(observer);
    }

    @Override
    public <T extends Entity> Collection<T> getTrackedEntities(Entity observer, Class<T> entityClass) {
        // access to a view of the multimap must be synchronized and we need to return a copy of that view
        synchronized (observerTrackedEntitiesMap) {
            //noinspection unchecked
            return (Collection<T>) observerTrackedEntitiesMap.get(observer).stream()
                    .filter(e -> e.getClass() == entityClass)
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public Collection<Player> getTrackedPlayers(Entity observer) {
        synchronized (observerTrackedEntitiesMap) {
            return getTrackedEntities(observer, Human.class).stream()
                    .map(playerManager::getOnlinePlayer)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public boolean isTracking(Entity observer, Entity entity) {
        return observerTrackedEntitiesMap.containsEntry(observer, entity);
    }

    private Collection<Entity> getInRangeEntities(Entity observer) {
        return nearbyEntityRetriever.getLoadedEntitiesInRadius(observer, maxTrackingDistance);
    }

    private void untrackEntity(Entity observer, Entity entity) {
        observerTrackedEntitiesMap.remove(observer, entity);
        CleanstoneServer.publishEvent(new EntityUntrackEvent(observer, entity));
    }

    private void trackEntity(Entity observer, Entity entity) {
        observerTrackedEntitiesMap.put(observer, entity);
        CleanstoneServer.publishEvent(new EntityTrackEvent(observer, entity));
    }

    @EventListener
    public void onEntityMove(EntityMoveEvent e) {
        Entity movingEntity = e.getEntity();
        if (ChunkCoords.of(e.getOldPosition()).equals(ChunkCoords.of(e.getNewPosition()))) {
            return;
        }
        Collection<Entity> inRangeEntities = getInRangeEntities(movingEntity);

        if (observerTrackedEntitiesMap.containsKey(movingEntity)) {
            // movingEntity as an observer
            Collection<Entity> currentEntities = observerTrackedEntitiesMap.get(movingEntity);
            synchronized (observerTrackedEntitiesMap) {
                inRangeEntities.stream()
                        .filter(updatedEntity -> !currentEntities.contains(updatedEntity))
                        .forEach(addedEntity -> trackEntity(movingEntity, addedEntity));
                currentEntities.stream()
                        .filter(currentEntity -> !inRangeEntities.contains(currentEntity))
                        .forEach(removedEntity -> untrackEntity(movingEntity, removedEntity));
            }
        }
        // movingEntity as an entity that can be tracked
        ChunkCoords entityChunkCoords = ChunkCoords.of(movingEntity.getPosition());
        inRangeEntities.forEach(inRangeEntity -> {
            int distance = entityChunkCoords.distance(ChunkCoords.of(inRangeEntity.getPosition()));

            if (distance > maxTrackingDistance && isTracking(inRangeEntity, movingEntity)) {
                untrackEntity(inRangeEntity, movingEntity);
            } else if (distance <= maxTrackingDistance && !isTracking(inRangeEntity, movingEntity)) {
                trackEntity(inRangeEntity, movingEntity);
            }
        });
    }
}
