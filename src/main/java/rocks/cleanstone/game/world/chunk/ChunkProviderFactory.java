package rocks.cleanstone.game.world.chunk;

import rocks.cleanstone.core.config.structs.WorldConfig;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.storage.world.WorldDataSource;

public interface ChunkProviderFactory {
    SimpleChunkProvider get(WorldConfig worldConfig, WorldDataSource worldDataSource, WorldGenerator worldGenerator);
}
