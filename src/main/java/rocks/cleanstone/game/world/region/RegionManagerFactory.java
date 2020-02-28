package rocks.cleanstone.game.world.region;

import rocks.cleanstone.game.world.chunk.ChunkProvider;

public interface RegionManagerFactory {
    RegionManager get(ChunkProvider chunkProvider);
}
