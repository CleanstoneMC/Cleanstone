package rocks.cleanstone.game.world.chunk;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.world.config.WorldConfig;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.storage.world.WorldDataSource;

@Component
public class SimpleChunkProviderFactory implements ChunkProviderFactory {

    @Override
    public SimpleChunkProvider get(WorldConfig worldConfig, WorldDataSource worldDataSource, WorldGenerator worldGenerator) {
        return new SimpleChunkProvider(worldConfig, worldDataSource, worldGenerator);
    }

}
