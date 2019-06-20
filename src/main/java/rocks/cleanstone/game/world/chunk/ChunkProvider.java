package rocks.cleanstone.game.world.chunk;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.storage.world.WorldDataSource;

public interface ChunkProvider {

    ListenableFuture<Chunk> getChunk(ChunkCoords coords);

    WorldGenerator getGenerator();

    WorldDataSource getDataSource();
}
