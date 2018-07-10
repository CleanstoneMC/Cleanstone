package rocks.cleanstone.game.world.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.leveldb.LevelDBDataSource;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.SimpleChunk;
import rocks.cleanstone.game.world.chunk.data.ChunkDataKeyFactory;
import rocks.cleanstone.game.world.chunk.data.StandardChunkDataType;
import rocks.cleanstone.game.world.chunk.data.block.BlockDataCodec;
import rocks.cleanstone.game.world.chunk.data.block.BlockDataStorage;

public class LevelDBWorldDataSource extends LevelDBDataSource implements WorldDataSource {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String worldID;
    private final boolean hasSkyLight;
    private final MaterialRegistry materialRegistry;

    public LevelDBWorldDataSource(File worldDataFolder, String worldID, MaterialRegistry materialRegistry)
            throws IOException {
        super(new File(worldDataFolder, worldID));
        this.worldID = worldID;
        this.materialRegistry = materialRegistry;

        // TODO read general world data (dimension, seed, etc)
        hasSkyLight = true;
    }

    @Nullable
    @Override
    public Chunk loadExistingChunk(int x, int y) {
        ByteBuf blocksKey = ChunkDataKeyFactory.create(x, y, StandardChunkDataType.BLOCKS);
        BlockDataStorage blockDataStorage;
        try {
            blockDataStorage = get(blocksKey, new BlockDataCodec(materialRegistry));
        } catch (IOException e) {
            logger.error("Failed to load corrupted chunk block data at " + x + ":" + y + " in LevelDB '"
                    + worldID + "'", e);
            return null;
        }
        blocksKey.release();
        if (blockDataStorage == null) return null;
        // TODO load blockEntities, entities, biome state, version
        return new SimpleChunk(blockDataStorage.constructTable(), blockDataStorage, Collections.emptyList(), x, y);
    }

    @Override
    public void saveChunk(Chunk chunk) {
        int x = chunk.getX(), y = chunk.getY();
        ByteBuf blocksKey = ChunkDataKeyFactory.create(x, y, StandardChunkDataType.BLOCKS);
        try {
            set(blocksKey, chunk.getBlockDataStorage(), new BlockDataCodec(materialRegistry));
        } catch (IOException e) {
            logger.error("Failed to save corrupted chunk block data at " + x + ":" + y + " in LevelDB '"
                    + worldID + "'", e);
        }
        blocksKey.release();
        // TODO save blockEntities, entities, biome state, version
    }
}
