package rocks.cleanstone.game.entity;

import com.google.common.base.Preconditions;
import com.google.common.collect.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.entity.event.*;
import rocks.cleanstone.game.entity.cleanstone.Human;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SimpleEntityTracker implements EntityTracker {
    private final NearbyEntityRetriever nearbyEntityRetriever;
    private final Multimap<Entity, Entity> observerTrackedEntitiesMap = Multimaps.synchronizedMultimap(HashMultimap.create());
    private final Set<Entity> observers = Sets.newConcurrentHashSet();
    private final PlayerManager playerManager;
    private final int maxTrackingDistance = 4;

    @Autowired
    public SimpleEntityTracker(NearbyEntityRetriever nearbyEntityRetriever, PlayerManager playerManager) {
        this.nearbyEntityRetriever = nearbyEntityRetriever;
        this.playerManager = playerManager;
    }

    @Override
    public void addObserver(Entity entity) {
        Preconditions.checkState(observers.add(entity), "given entity is already an observer");
        Collection<Entity> inRangeEntities = getInRangeEntities(entity);
        makeTheObserverTrackInRangeEntities(entity, inRangeEntities);
    }

    @Override
    public void removeObserver(Entity observer) {
        Preconditions.checkState(observers.remove(observer), "given entity is not an observer");
        makeTheObserverUntrackAllEntities(observer);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity> Collection<T> getTrackedEntities(Entity observer, Class<T> entityClass) {
        // access to a view of the multimap must be synchronized and we need to return a copy of that view
        synchronized (observerTrackedEntitiesMap) {
            return (Collection<T>) observerTrackedEntitiesMap.get(observer).stream()
                    .filter(e -> e.getClass() == entityClass)
                    .collect(Collectors.toSet());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity> Collection<T> getObservers(Entity trackedEntity, Class<T> observerClass) {
        synchronized (observerTrackedEntitiesMap) {
            return (Collection<T>) observerTrackedEntitiesMap.entries().stream()
                    .filter(entry -> observerClass.isAssignableFrom(entry.getKey().getClass()) || entry.getKey().getClass() == observerClass)
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

    @Override
    public boolean isObserver(Entity entity) {
        return observers.contains(entity);
    }

    private Collection<Entity> getInRangeEntities(Entity observer) {
        return nearbyEntityRetriever.getLoadedEntitiesInRadius(observer, maxTrackingDistance);
    }

    private void untrackEntity(Entity observer, Entity entity) {
        log.debug("observer {} now does not track {}", observer, entity);
        Preconditions.checkState(observerTrackedEntitiesMap.remove(observer, entity),
                "given observer is not tracking given entity");
        CleanstoneServer.publishEvent(new EntityUntrackEvent(observer, entity));
    }

    private void trackEntity(Entity observer, Entity entity) {
        log.debug("observer {} now tracks {}", observer, entity);
        Preconditions.checkState(observerTrackedEntitiesMap.put(observer, entity),
                "given observer is already tracking given entity");
        CleanstoneServer.publishEvent(new EntityTrackEvent(observer, entity));
    }

    @Override
    @Async
    @EventListener
    public synchronized void onEntityMove(EntityMoveEvent e) {
        Entity movingEntity = e.getEntity();
        // When the entity is in the same Chunk, just return
        if (ChunkCoords.of(e.getOldPosition()).equals(ChunkCoords.of(e.getNewPosition()))) {
            return;
        }
        Collection<Entity> inRangeEntities = getInRangeEntities(movingEntity);

        if (isObserver(movingEntity)) {
            makeTheObserverUntrackOutOfRangeEntities(movingEntity, inRangeEntities);
            makeTheObserverTrackInRangeEntities(movingEntity, inRangeEntities);
        }

        makeInRangeObserversTrackTheEntity(movingEntity, inRangeEntities);
        makeOutOfRangeObserversUntrackTheEntity(movingEntity, inRangeEntities);
    }

    @Override
    @Async
    @EventListener
    public synchronized void onEntityAdd(EntityAddEvent event) {
        Collection<Entity> inRangeEntities = getInRangeEntities(event.getEntity());

        makeInRangeObserversTrackTheEntity(event.getEntity(), inRangeEntities);
    }

    @Override
    @Async
    @EventListener
    public synchronized void onEntityRemove(EntityRemoveEvent event) {
        Entity entity = event.getEntity();

        if (isObserver(entity)) {
            makeTheObserverUntrackAllEntities(entity);
        }

        makeAllObserversUntrackTheEntity(entity);
    }

    private void makeTheObserverUntrackOutOfRangeEntities(Entity observer, Collection<Entity> inRangeEntities) {
        // All entities that the observer is currently tracking
        Collection<Entity> currentEntities = observerTrackedEntitiesMap.get(observer);
        ImmutableSet.copyOf(currentEntities).stream()
                // that are now out of range
                .filter(currentlyTrackedEntity -> !inRangeEntities.contains(currentlyTrackedEntity))
                // should be untracked by the observer
                .forEach(outOfRangeEntity -> untrackEntity(observer, outOfRangeEntity));
    }

    private void makeTheObserverTrackInRangeEntities(Entity observer, Collection<Entity> inRangeEntities) {
        // All entities that are in range of the observer
        inRangeEntities.stream()
                // that are not already tracked by the observer
                .filter(inRangeEntity -> !isTracking(observer, inRangeEntity))
                // should be tracked by the observer
                .forEach(inRangeEntity -> trackEntity(observer, inRangeEntity));
    }

    private void makeInRangeObserversTrackTheEntity(Entity entity, Collection<Entity> inRangeEntities) {
        // All entities that are in range of the entity
        inRangeEntities.stream()
                // that are observers
                .filter(this::isObserver)
                // that are not already tracking the entity
                .filter(observer -> !isTracking(observer, entity))
                // should track the entity
                .forEach(observer -> trackEntity(observer, entity));
    }

    private void makeOutOfRangeObserversUntrackTheEntity(Entity entity, Collection<Entity> inRangeEntities) {
        ChunkCoords entityChunkCoords = ChunkCoords.of(entity.getPosition());

        // In all observer-trackedEntity pairs
        ImmutableSet.copyOf(observerTrackedEntitiesMap.entries()).stream()
                // where the tracked entity is the entity
                .filter(entry -> entry.getValue() == entity)
                // where the observer is out of range of the entity
                .filter(entry -> !inRangeEntities.contains(entry.getKey()))
                // the observer should untrack the entity
                .forEach(entry -> {
                    Entity observer = entry.getKey();
                    untrackEntity(observer, entity);
                });
    }

    private void makeTheObserverUntrackAllEntities(Entity observer) {
        // All entities that the observer is currently tracking
        Collection<Entity> currentEntities = observerTrackedEntitiesMap.get(observer);
        ImmutableSet.copyOf(currentEntities)
                // should be untracked by the observer
                .forEach(entity -> untrackEntity(observer, entity));
    }

    private void makeAllObserversUntrackTheEntity(Entity entity) {
        // All observers
        Collection<Entity> currentEntities = observerTrackedEntitiesMap.keySet();
        ImmutableSet.copyOf(currentEntities).stream()
                // that are tracking the entity
                .filter(observer -> isTracking(observer, entity))
                // should untrack the entity
                .forEach(observer -> untrackEntity(observer, entity));
    }
}
