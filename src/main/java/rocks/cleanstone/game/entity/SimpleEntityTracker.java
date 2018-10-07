package rocks.cleanstone.game.entity;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.entity.event.EntityAddEvent;
import rocks.cleanstone.game.entity.event.EntityMoveEvent;
import rocks.cleanstone.game.entity.event.EntityRemoveEvent;
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
        Collection<Entity> inRangeEntities = getInRangeEntities(observer);
        observerTrackedEntitiesMap.putAll(observer, inRangeEntities);
        inRangeEntities.forEach(addedEntity -> trackEntity(observer, addedEntity));
    }

    @Override
    public void removeObserver(Entity observer) {
        synchronized (observerTrackedEntitiesMap) {
            Iterator<Entity> iterator = observerTrackedEntitiesMap.get(observer).iterator();
            while (iterator.hasNext()) {
                Entity entity = iterator.next();
                untrackEntity(observer, entity);
                iterator.remove();
            }
        }
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
    public <T extends Entity> Collection<T> getObservers(Entity trackedEntity, Class<T> observerClass) {
        synchronized (observerTrackedEntitiesMap) {
            //noinspection unchecked
            return (Collection<T>) observerTrackedEntitiesMap.entries().stream()
                    .filter(entry -> entry.getKey().getClass() == observerClass)
                    .filter(entry -> entry.getValue() == trackedEntity)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public Collection<Player> getPlayerObservers(Entity trackedEntity) {
        return getObservers(trackedEntity, Human.class).stream()
                .map(playerManager::getOnlinePlayer)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<Player> getTrackedPlayers(Entity observer) {
        return getTrackedEntities(observer, Human.class).stream()
                .map(playerManager::getOnlinePlayer)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isTracking(Entity observer, Entity entity) {
        return observerTrackedEntitiesMap.containsEntry(observer, entity);
    }

    private Collection<Entity> getInRangeEntities(Entity observer) {
        return nearbyEntityRetriever.getLoadedEntitiesInRadius(observer, maxTrackingDistance);
    }

    private void untrackEntity(Entity observer, Entity entity) {
        CleanstoneServer.publishEvent(new EntityUntrackEvent(observer, entity));
    }

    private void trackEntity(Entity observer, Entity entity) {
        CleanstoneServer.publishEvent(new EntityTrackEvent(observer, entity));
    }

    @Async
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
        // movingEntity moves into tracking range
        inRangeEntities.stream()
                .filter(inRangeEntity -> !isTracking(inRangeEntity, movingEntity))
                .forEach(inRangeEntity -> {
                    observerTrackedEntitiesMap.put(inRangeEntity, movingEntity);
                    trackEntity(inRangeEntity, movingEntity);
                });
        // movingEntity moves out of tracking range
        ChunkCoords entityChunkCoords = ChunkCoords.of(movingEntity.getPosition());
        synchronized (observerTrackedEntitiesMap) {
            ImmutableSet.copyOf(observerTrackedEntitiesMap.entries()).stream()
                    .filter(entry -> entry.getValue() == movingEntity)
                    .forEach(entry -> {
                        Entity observer = entry.getKey();
                        int distance = entityChunkCoords.distance(ChunkCoords.of(observer.getPosition()));
                        if (distance > maxTrackingDistance) {
                            observerTrackedEntitiesMap.remove(observer, movingEntity);
                            untrackEntity(observer, movingEntity);
                        }
                    });
        }
    }

    @Async
    @EventListener
    public void onEntityAdd(EntityAddEvent event) {
        Collection<Entity> inRangeEntities = getInRangeEntities(event.getEntity());
        // TODO update in-range observers

    }

    @Async
    @EventListener
    public void onEntityRemove(EntityRemoveEvent event) {
        Collection<Entity> inRangeEntities = getInRangeEntities(event.getEntity());
        // TODO update in-range observers

    }
}
