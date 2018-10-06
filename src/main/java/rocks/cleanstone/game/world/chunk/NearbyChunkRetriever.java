package rocks.cleanstone.game.world.chunk;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;

import rocks.cleanstone.game.world.World;

public interface NearbyChunkRetriever {
    Collection<ChunkCoords> getChunkCoordsAround(ChunkCoords coords, int radius);

    ListenableFuture<Collection<Chunk>> getChunksAround(ChunkCoords coords, int radius, World world);

    Collection<Chunk> getLoadedChunksAround(ChunkCoords coords, int radius, World world);
}
