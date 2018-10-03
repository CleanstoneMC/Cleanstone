package rocks.cleanstone.game.world.region;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;

import javax.annotation.Nullable;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.game.world.chunk.ChunkProvider;
import rocks.cleanstone.game.world.event.ChunkLoadedEvent;
import rocks.cleanstone.game.world.event.ChunkUnloadEvent;

public class SimpleRegion implements Region {

    private final String id;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ChunkProvider chunkProvider;
    private Cache<ChunkCoords, Chunk> loadedChunks = CacheBuilder.newBuilder()
            .maximumSize(4096)
            .removalListener(this::removeChunkListener)
            .build();

    public SimpleRegion(String id, Collection<Chunk> loadedChunks, RegionWorker regionWorker,
                        ChunkProvider chunkProvider) {
        this.id = id;
        loadedChunks.forEach(chunk -> this.loadedChunks.put(chunk.getCoordinates(), chunk));
        this.chunkProvider = chunkProvider;
    }

    public SimpleRegion(String id, RegionWorker regionWorker, ChunkProvider chunkProvider) {
        this(id, Sets.newConcurrentHashSet(), regionWorker, chunkProvider);
    }

    private void removeChunkListener(RemovalNotification removalNotification) {
        Chunk chunk = (Chunk) removalNotification.getValue();
        if (chunk.hasUnsavedChanges()) {
            this.chunkProvider.getDataSource().saveChunk(chunk);
        }
    }

    @Override
    public Collection<Chunk> getLoadedChunks() {
        return ImmutableSet.copyOf(loadedChunks.asMap().values());
    }

    @Override
    public boolean isChunkLoaded(ChunkCoords coords) {
        return loadedChunks.asMap().containsKey(coords);
    }

    @Nullable
    @Override
    public Chunk getLoadedChunk(ChunkCoords coords) {
        return loadedChunks.getIfPresent(coords);
    }

    @Override
    public ListenableFuture<Chunk> getChunk(ChunkCoords coords) {
        if (isChunkLoaded(coords)) {
            return new AsyncResult<>(getLoadedChunk(coords));
        }
        return loadChunk(coords);
    }

    @Override
    public ListenableFuture<Chunk> loadChunk(ChunkCoords coords) {
        ListenableFuture<Chunk> chunkFuture = chunkProvider.getChunk(coords);
        chunkFuture.addCallback(
                chunk -> {
                    Preconditions.checkNotNull(chunk);
                    loadedChunks.put(coords, chunk);
                    CleanstoneServer.publishEvent(new ChunkLoadedEvent(chunk));
                },
                error -> logger.error("could not load chunk " + coords, error)
        );
        return chunkFuture;
    }

    @Override
    public void unloadChunk(ChunkCoords coords) {
        Chunk chunk = getLoadedChunk(coords);
        Preconditions.checkNotNull(chunk, "Cannot unload non-loaded chunk " + coords);
        CleanstoneServer.publishEvent(new ChunkUnloadEvent(chunk));
        loadedChunks.invalidate(coords);
    }

    @Override
    public void unloadAllChunks() {
        loadedChunks.invalidateAll();
    }

    @Override
    public String getID() {
        return id;
    }
}
