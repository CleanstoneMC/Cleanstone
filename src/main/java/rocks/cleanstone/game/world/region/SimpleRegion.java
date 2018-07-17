package rocks.cleanstone.game.world.region;

import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkProvider;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class SimpleRegion implements Region {

    private final RegionWorker regionWorker;
    private final String id;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ChunkProvider chunkProvider;
    private final LoadingCache<Pair<Integer, Integer>, Chunk> loadedChunks = CacheBuilder.newBuilder()
            .maximumSize(1024)
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .expireAfterAccess(15, TimeUnit.MINUTES)
            .removalListener(this::removeChunkListener)
            .build(new CacheLoader<Pair<Integer, Integer>, Chunk>() {
                @Override
                public Chunk load(Pair<Integer, Integer> chunkCoordPair) {
                    ListenableFuture<Chunk> chunkFuture = chunkProvider.getChunk(chunkCoordPair.getLeft(), chunkCoordPair.getRight());

                    chunkFuture.addCallback(chunk -> loadedChunks.put(Pair.of(chunk.getX(), chunk.getY()), chunk),
                            (error) -> logger.error("Failed to load chunk " + chunkCoordPair.getLeft() + ":" + chunkCoordPair.getRight(), error));

                    try {
                        return chunkFuture.get();
                    } catch (InterruptedException | ExecutionException e) {
                        return null;
                    }
                }
            });

    public SimpleRegion(String id, Collection<Chunk> loadedChunks, RegionWorker regionWorker,
                        ChunkProvider chunkProvider) {
        this.id = id;
        loadedChunks.forEach(chunk -> this.loadedChunks.put(Pair.of(chunk.getX(), chunk.getY()), chunk));
        this.regionWorker = regionWorker;
        this.chunkProvider = chunkProvider;
    }

    public SimpleRegion(String id, RegionWorker regionWorker, ChunkProvider chunkProvider) {
        this(id, Sets.newConcurrentHashSet(), regionWorker, chunkProvider);
    }

    private void removeChunkListener(RemovalNotification removalNotification) {
        this.chunkProvider.getDataSource().saveChunk((Chunk) removalNotification.getValue());
    }

    @Override
    public Collection<Chunk> getLoadedChunks() {
        return ImmutableSet.copyOf(loadedChunks.asMap().values());
    }

    @Override
    public boolean isChunkLoaded(int chunkX, int chunkY) {
        return getLoadedChunk(chunkX, chunkY) != null;
    }

    @Nullable
    @Override
    public Chunk getLoadedChunk(int chunkX, int chunkY) {
        return loadedChunks.asMap().get(Pair.of(chunkX, chunkY));
    }

    @Override
    public ListenableFuture<Chunk> getChunk(int chunkX, int chunkY) {
        try {
            return new AsyncResult<>(loadedChunks.get(Pair.of(chunkX, chunkY)));
        } catch (ExecutionException e) {
            return new AsyncResult<>(null);
        }
    }

    @Override
    public ListenableFuture<Chunk> loadChunk(int chunkX, int chunkY) {
        return getChunk(chunkX, chunkY);
    }

    @Override
    public void unloadChunk(int chunkX, int chunkY) {
        Chunk chunk = getLoadedChunk(chunkX, chunkY);
        Preconditions.checkNotNull(chunk, "Cannot unload non-loaded chunk " + chunkX + ":" + chunkY);

        loadedChunks.invalidate(Pair.of(chunk.getX(), chunk.getY()));
    }

    @Override
    public String getID() {
        return id;
    }
}
