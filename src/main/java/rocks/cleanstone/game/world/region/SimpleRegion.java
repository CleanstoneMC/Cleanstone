package rocks.cleanstone.game.world.region;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkProvider;
import rocks.cleanstone.game.world.event.ChunkLoadedEvent;
import rocks.cleanstone.game.world.event.ChunkUnloadEvent;

import javax.annotation.Nullable;
import java.util.Collection;

public class SimpleRegion implements Region {

    private final String id;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ChunkProvider chunkProvider;
    private Cache<Pair<Integer, Integer>, Chunk> loadedChunks = CacheBuilder.newBuilder()
            .maximumSize(4096)
            .removalListener(this::removeChunkListener)
            .build();

    public SimpleRegion(String id, Collection<Chunk> loadedChunks, RegionWorker regionWorker,
                        ChunkProvider chunkProvider) {
        this.id = id;
        loadedChunks.forEach(chunk -> this.loadedChunks.put(Pair.of(chunk.getX(), chunk.getZ()), chunk));
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
    public boolean isChunkLoaded(int chunkX, int chunkZ) {
        return loadedChunks.asMap().containsKey(Pair.of(chunkX, chunkZ));
    }

    @Nullable
    @Override
    public Chunk getLoadedChunk(int chunkX, int chunkZ) {
        return loadedChunks.getIfPresent(Pair.of(chunkX, chunkZ));
    }

    @Override
    public ListenableFuture<Chunk> getChunk(int chunkX, int chunkZ) {
        if (isChunkLoaded(chunkX, chunkZ)) {
            return new AsyncResult<>(getLoadedChunk(chunkX, chunkZ));
        }
        return loadChunk(chunkX, chunkZ);
    }

    @Override
    public ListenableFuture<Chunk> loadChunk(int chunkX, int chunkZ) {
        ListenableFuture<Chunk> chunkFuture = chunkProvider.getChunk(chunkX, chunkZ);
        chunkFuture.addCallback(
                chunk -> {
                    Preconditions.checkNotNull(chunk);
                    loadedChunks.put(Pair.of(chunkX, chunkZ), chunk);
                    CleanstoneServer.publishEvent(new ChunkLoadedEvent(chunk));
                },
                error -> logger.error("could not load chunk " + chunkX + ", " + chunkZ, error)
        );
        return chunkFuture;
    }

    @Override
    public void unloadChunk(int chunkX, int chunkZ) {
        Chunk chunk = getLoadedChunk(chunkX, chunkZ);
        Preconditions.checkNotNull(chunk, "Cannot unload non-loaded chunk " + chunkX + ":" + chunkZ);
        CleanstoneServer.publishEvent(new ChunkUnloadEvent(chunk));
        loadedChunks.invalidate(Pair.of(chunk.getX(), chunk.getZ()));
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
