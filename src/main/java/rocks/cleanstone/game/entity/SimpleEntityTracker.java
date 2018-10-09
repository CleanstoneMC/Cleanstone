package rocks.cleanstone.game.entity;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.entity.event.*;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SimpleEntityTracker implements EntityTracker {
    private final Logger logger = LoggerFactory.getLogger(SimpleEntityTracker.class);
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

    /**
     * Is the given Observer Tracking the given Entity
     *
     * @param observer The Entity who observes
     * @param entity   The entity which is checked
     * @return does the Observer track the given Entity
     */
    @Override
    public boolean isTracking(Entity observer, Entity entity) {
        return observerTrackedEntitiesMap.containsEntry(observer, entity);
    }

    private Collection<Entity> getInRangeEntities(Entity observer) {
        return nearbyEntityRetriever.getLoadedEntitiesInRadius(observer, maxTrackingDistance);
    }

    private void untrackEntity(Entity observer, Entity entity) {
        logger.debug("observer {} now does not track {}", observer, entity);
        observerTrackedEntitiesMap.remove(observer, entity);
        CleanstoneServer.publishEvent(new EntityUntrackEvent(observer, entity));
    }

    private void trackEntity(Entity observer, Entity entity) {
        logger.debug("observer {} now tracks {}", observer, entity);
        observerTrackedEntitiesMap.put(observer, entity);
        CleanstoneServer.publishEvent(new EntityTrackEvent(observer, entity));
    }

    @Override
    @Async
    @EventListener
    public void onEntityMove(EntityMoveEvent e) {
        Entity movingEntity = e.getEntity();

        // When the entity is in the same Chunk, just return
        if (ChunkCoords.of(e.getOldPosition()).equals(ChunkCoords.of(e.getNewPosition()))) {
            return;
        }

        Collection<Entity> inRangeEntities = getInRangeEntities(movingEntity);

        // If the movingEntity is registered as an Observer
        if (observerTrackedEntitiesMap.containsKey(movingEntity)) {
            removeTrackEntriesForEntitiesOutOfRange(movingEntity, inRangeEntities);
        }

        addTrackEntriesForEachInRangeEntity(movingEntity, inRangeEntities);

        removeTrackEntriesForObservedEntitiesOutOfRange(movingEntity);
    }

    /**
     * Iterate over the Entities in Range and untrack the entities out of range
     * @param trackingEntity The Entity that is currently tracking other Entities
     * @param inRangeEntities The Entities to iterate over and check
     */
    private void removeTrackEntriesForEntitiesOutOfRange(Entity trackingEntity, Collection<Entity> inRangeEntities) {
        // All Entities that the moving Entity is tracking
        Collection<Entity> currentEntities = observerTrackedEntitiesMap.get(trackingEntity);
        synchronized (observerTrackedEntitiesMap) {
            // All Entities that are currently tracked
            currentEntities.stream()
                    // Filter for Entities that are currently not in Range
                    .filter(currentlyTrackedEntity -> !inRangeEntities.contains(currentlyTrackedEntity))
                    // For each tracked entity that is now not in range, untrack it
                    .forEach(outOfRangeEntity -> untrackEntity(trackingEntity, outOfRangeEntity));
        }
    }

    /**
     * Iterate over the Entities in range and register them to track the moving Entity when they currently don't do that
     *
     * @param movingEntity    The Moving Entity
     * @param inRangeEntities The Entities to iterate over and check
     */
    private void addTrackEntriesForEachInRangeEntity(Entity movingEntity, Collection<Entity> inRangeEntities) {
        inRangeEntities.stream()
                // Filter for entities in range that are not tracking the moving Entity
                .filter(inRangeEntity -> !isTracking(inRangeEntity, movingEntity))
                // For each Entity which does not track the moving Entity
                .forEach(inRangeEntity -> {
                    // The Entity which currently does not track the moving Entity but should because its in range, now tracks it
                    trackEntity(inRangeEntity, movingEntity);
                });
    }

    /**
     * Iterate over all Entities to check if they have a registered Tracker on the given Entity and when its out of range remove it from the tracking list
     *
     * @param entity The Moving Entity
     */
    private void removeTrackEntriesForObservedEntitiesOutOfRange(Entity entity) {
        ChunkCoords entityChunkCoords = ChunkCoords.of(entity.getPosition());

        synchronized (observerTrackedEntitiesMap) {
            // Get a Immutable Copy of all Entities that are observed
            ImmutableSet.copyOf(observerTrackedEntitiesMap.entries()).stream()
                    // Filter for the given Entity
                    .filter(entry -> entry.getValue() == entity)
                    // For each of those Entries check if its out of range and remove it then
                    .forEach(entry -> {
                        Entity observer = entry.getKey();
                        int distance = entityChunkCoords.distance(ChunkCoords.of(observer.getPosition()));
                        if (distance > maxTrackingDistance) {
                            untrackEntity(observer, entity);
                        }
                    });
        }
    }

    @Override
    @Async
    @EventListener
    public void onEntityAdd(EntityAddEvent event) {
        Collection<Entity> inRangeEntities = getInRangeEntities(event.getEntity());
        // TODO update in-range observers

    }

    @Override
    @Async
    @EventListener
    public void onEntityRemove(EntityRemoveEvent event) {
        Collection<Entity> inRangeEntities = getInRangeEntities(event.getEntity());
        // TODO update in-range observers

    }
}
