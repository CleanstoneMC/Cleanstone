package rocks.cleanstone.game.world.region;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Nullable;

import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.region.chunk.Chunk;
import rocks.cleanstone.game.world.region.chunk.SimpleChunk;

public class SimpleRegion implements Region {

    private final Chunk[][] chunks;
    private final RegionWorker regionWorker;
    private final World world;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final int x, y;

    public SimpleRegion(int x, int y, Chunk[][] chunks, RegionWorker regionWorker, World world) {
        this.chunks = chunks;
        this.regionWorker = regionWorker;
        this.world = world;
        this.x = x;
        this.y = y;
    }

    public SimpleRegion(int x, int y, RegionWorker regionWorker, World world) {
        this(x, y, new SimpleChunk[Region.CHUNK_COUNT_ROOT][Region.CHUNK_COUNT_ROOT], regionWorker, world);
    }

    @Override
    public Collection<Chunk> getLoadedChunks() {
        return Arrays.asList((Chunk[]) Arrays.stream(chunks).toArray());
    }

    @Override
    public boolean isChunkLoaded(int x, int y) {
        return chunks[x][y] != null;
    }

    @Nullable
    @Override
    public Chunk getLoadedChunk(int x, int y) {
        return chunks[x][y];
    }

    @Override
    public ListenableFuture<Chunk> getChunk(int x, int y) {
        if (isChunkLoaded(x, y)) {
            return new AsyncResult<>(getLoadedChunk(x, y));
        }
        ListenableFuture<Chunk> chunkFuture = world.getChunkProvider().getChunk(x, y);
        chunkFuture.addCallback((chunk) -> chunks[x][y] = chunk,
                (error) -> logger.error("Failed to get non-loaded chunk " + x + ":" + y, error));
        return chunkFuture;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public World getWorld() {
        return world;
    }
}
