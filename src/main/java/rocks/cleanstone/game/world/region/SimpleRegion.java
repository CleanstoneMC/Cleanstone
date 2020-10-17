package rocks.cleanstone.game.world.region;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.game.world.chunk.ChunkProvider;
import rocks.cleanstone.game.world.event.ChunkLoadedEvent;
import rocks.cleanstone.game.world.event.ChunkSaveEvent;
import rocks.cleanstone.game.world.event.ChunkUnloadEvent;

import javax.annotation.Nullable;
import java.util.Collection;

@Slf4j
public class SimpleRegion implements Region {

    private final String id;
    private final ChunkProvider chunkProvider;
    private final Cache<ChunkCoords, Chunk> loadedChunks = CacheBuilder.newBuilder()
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

    private void removeChunkListener(RemovalNotification<ChunkCoords, Chunk> removalNotification) {
        Chunk chunk = removalNotification.getValue();
        unloadChunk(chunk);
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
                error -> log.error("could not load chunk " + coords, error)
        );
        return chunkFuture;
    }

    @Override
    public void unloadChunk(ChunkCoords coords) {
        Chunk chunk = getLoadedChunk(coords);
        unloadChunk(chunk);
        loadedChunks.invalidate(coords);
    }

    private void unloadChunk(Chunk chunk) {
        Preconditions.checkNotNull(chunk, "Cannot unload non-loaded chunk " + chunk.getCoordinates());
        if (chunk.hasUnsavedChanges()) {
            CleanstoneServer.publishEvent(new ChunkSaveEvent(chunk));
            this.chunkProvider.getDataSource().saveChunk(chunk);
        }
        CleanstoneServer.publishEvent(new ChunkUnloadEvent(chunk));
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
