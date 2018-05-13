package rocks.cleanstone.game.world.region;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.world.region.chunk.ArrayChunkTable;
import rocks.cleanstone.game.world.region.chunk.Chunk;
import rocks.cleanstone.game.world.region.chunk.ChunkTable;
import rocks.cleanstone.game.world.region.chunk.SimpleChunk;
import rocks.cleanstone.io.data.world.WorldDataSource;

import java.util.HashSet;

public class LocalRegionWorker implements RegionWorker {

    private final WorldDataSource dataSource;

    public LocalRegionWorker(WorldDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Async("worldLoadingExec")
    @Override
    public ListenableFuture<Chunk> loadChunk(int x, int y) {
        // TODO load from dataSource
        ChunkTable table = new ArrayChunkTable();

        // Stone platform
        for (int rx = 0; rx < Chunk.WIDTH; rx++) {
            for (int rz = 0; rz < Chunk.WIDTH; rz++) {
                table.setBlock(rx, 70, rz, ImmutableBlock.of(VanillaMaterial.STONE));
            }
        }

        return new AsyncResult<>(new SimpleChunk(new HashSet<>(), table, x, y));
    }

    @Override
    public void unloadChunk(int x, int y) {
        // TODO
    }
}
