package rocks.cleanstone.game.world.chunk;

import org.springframework.util.concurrent.ListenableFuture;

import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.WorldGenerator;

public interface ChunkProvider {

    ListenableFuture<Chunk> getChunk(int x, int y);

    WorldGenerator getGenerator();

    WorldDataSource getDataSource();
}
