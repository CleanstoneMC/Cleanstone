package rocks.cleanstone.game.world.chunk;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.World;

import java.util.Collection;

public interface NearbyChunkRetriever {
    Collection<ChunkCoords> getChunkCoordsAround(int chunkX, int chunkZ, int radius);

    ListenableFuture<Collection<Chunk>> getChunksAround(int chunkX, int chunkZ, int radius, World world);
}
