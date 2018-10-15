package rocks.cleanstone.game.world.chunk;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.World;

import java.util.Collection;

public interface NearbyChunkRetriever {
    Collection<ChunkCoords> getChunkCoordsAround(ChunkCoords coords, int radius);

    ListenableFuture<Collection<Chunk>> getChunksAround(ChunkCoords coords, int radius, World world);

    Collection<Chunk> getLoadedChunksAround(ChunkCoords coords, int radius, World world);
}
