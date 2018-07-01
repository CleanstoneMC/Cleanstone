package rocks.cleanstone.game.world.region;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;

import javax.annotation.Nullable;

import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkProvider;

public class SimpleRegion implements Region {

    private final Collection<Chunk> loadedChunks;
    private final RegionWorker regionWorker;
    private final String id;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ChunkProvider chunkProvider;

    public SimpleRegion(String id, Collection<Chunk> loadedChunks, RegionWorker regionWorker,
                        ChunkProvider chunkProvider) {
        this.id = id;
        this.loadedChunks = loadedChunks;
        this.regionWorker = regionWorker;
        this.chunkProvider = chunkProvider;
        // TODO unload chunks (use guava cache?)
    }

    public SimpleRegion(String id, RegionWorker regionWorker, ChunkProvider chunkProvider) {
        this(id, Sets.newConcurrentHashSet(), regionWorker, chunkProvider);
    }

    @Override
    public Collection<Chunk> getLoadedChunks() {
        return ImmutableSet.copyOf(loadedChunks);
    }

    @Override
    public boolean isChunkLoaded(int chunkX, int chunkY) {
        return getLoadedChunk(chunkX, chunkY) != null;
    }

    @Nullable
    @Override
    public Chunk getLoadedChunk(int chunkX, int chunkY) {
        return loadedChunks.stream().filter(chunk -> chunk.getX() == chunkX && chunk.getY() == chunkY)
                .findAny().orElse(null);
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
        chunkFuture.addCallback(loadedChunks::add,
                (error) -> logger.error("Failed to load chunk " + chunkX + ":" + chunkY, error));
        return chunkFuture;
    }

    @Override
    public void unloadChunk(int chunkX, int chunkY) {
        Chunk chunk = getLoadedChunk(chunkX, chunkY);
        Preconditions.checkNotNull(chunk, "Cannot unload non-loaded chunk " + chunkX + ":" + chunkY);
        loadedChunks.remove(chunk);
    }

    @Override
    public String getID() {
        return id;
    }
}
