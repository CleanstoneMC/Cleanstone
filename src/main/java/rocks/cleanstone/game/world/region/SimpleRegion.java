package rocks.cleanstone.game.world.region;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
import javax.annotation.Nullable;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkProvider;

public class SimpleRegion implements Region {

    private final String id;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ChunkProvider chunkProvider;
    private Cache<Pair<Integer, Integer>, Chunk> loadedChunks = CacheBuilder.newBuilder()
            .maximumSize(1024)
            .removalListener(this::removeChunkListener)
            .build();

    public SimpleRegion(String id, Collection<Chunk> loadedChunks, RegionWorker regionWorker,
                        ChunkProvider chunkProvider) {
        this.id = id;
        loadedChunks.forEach(chunk -> this.loadedChunks.put(Pair.of(chunk.getX(), chunk.getY()), chunk));
        this.chunkProvider = chunkProvider;
    }

    public SimpleRegion(String id, RegionWorker regionWorker, ChunkProvider chunkProvider) {
        this(id, Sets.newConcurrentHashSet(), regionWorker, chunkProvider);
    }

    private void removeChunkListener(RemovalNotification removalNotification) {
        Chunk chunk = (Chunk) removalNotification.getValue();
        if (chunk.isDirty()) {
            this.chunkProvider.getDataSource().saveChunk(chunk);
        }
    }

    @Override
    public Collection<Chunk> getLoadedChunks() {
        return ImmutableSet.copyOf(loadedChunks.asMap().values());
    }

    @Override
    public boolean isChunkLoaded(int chunkX, int chunkY) {
        return loadedChunks.asMap().containsKey(Pair.of(chunkX, chunkY));
    }

    @Nullable
    @Override
    public Chunk getLoadedChunk(int chunkX, int chunkY) {
        return loadedChunks.getIfPresent(Pair.of(chunkX, chunkY));
    }

    @Override
    public ListenableFuture<Chunk> getChunk(int chunkX, int chunkY) {
        if (isChunkLoaded(chunkX, chunkY)) {
            return new AsyncResult<>(getLoadedChunk(chunkX, chunkY));
        }

        return loadChunk(chunkX, chunkY);
    }

    @Override
    public ListenableFuture<Chunk> loadChunk(int chunkX, int chunkY) {
        ListenableFuture<Chunk> chunkFuture = chunkProvider.getChunk(chunkX, chunkY);
        chunkFuture.addCallback(
                chunk -> loadedChunks.put(Pair.of(chunkX, chunkY), chunk),
                error -> logger.error("could not load chunk " + chunkX + ", " + chunkY, error)
        );
        return chunkFuture;
    }

    @Override
    public void unloadChunk(int chunkX, int chunkY) {
        Chunk chunk = getLoadedChunk(chunkX, chunkY);
        Preconditions.checkNotNull(chunk, "Cannot unload non-loaded chunk " + chunkX + ":" + chunkY);

        loadedChunks.invalidate(Pair.of(chunk.getX(), chunk.getY()));
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
