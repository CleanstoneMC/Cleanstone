package rocks.cleanstone.game.world.region;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.world.chunk.ChunkProvider;

@Component
public class SingleRegionManagerFactory implements RegionManagerFactory {
    @Override
    public RegionManager get(ChunkProvider chunkProvider) {
        return new SingleRegionManager(chunkProvider);
    }
}