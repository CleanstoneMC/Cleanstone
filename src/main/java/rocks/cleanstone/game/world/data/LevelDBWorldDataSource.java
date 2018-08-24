package rocks.cleanstone.game.world.data;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import rocks.cleanstone.data.Codec;
import rocks.cleanstone.data.EnumCodec;
import rocks.cleanstone.data.leveldb.LevelDBDataSource;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.SimpleChunk;
import rocks.cleanstone.game.world.chunk.data.ChunkDataKeyFactory;
import rocks.cleanstone.game.world.chunk.data.StandardChunkDataType;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.DirectPalette;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataCodec;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorage;
import rocks.cleanstone.net.minecraft.protocol.v1_13.ProtocolBlockStateMapping;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

@Component
@Scope("prototype")
public class LevelDBWorldDataSource extends LevelDBDataSource implements WorldDataSource {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String worldID;
    private final boolean hasSkyLight;
    private final DirectPalette directPalette = new DirectPalette(new ProtocolBlockStateMapping(), 14);

    public LevelDBWorldDataSource(File worldDataFolder, String worldID)
            throws IOException {
        super(new File(worldDataFolder, worldID));
        this.worldID = worldID;

        // TODO read general world data (dimension, seed, etc)
        hasSkyLight = true;
    }

    @Nullable
    @Override
    public Chunk loadExistingChunk(int x, int y) {
        ByteBuf blocksKey = ChunkDataKeyFactory.create(x, y, StandardChunkDataType.BLOCKS);
        VanillaBlockDataStorage blockDataStorage;
        Codec<VanillaBlockDataStorage, ByteBuf> blockDataCodec = new VanillaBlockDataCodec(directPalette, true);
        try {
            blockDataStorage = get(blocksKey, blockDataCodec);
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
        logger.trace("persisting chunk {}, {}", chunk.getX(), chunk.getY());
        int x = chunk.getX(), y = chunk.getY();
        ByteBuf versionKey = ChunkDataKeyFactory.create(x, y, StandardChunkDataType.VERSION);
        ByteBuf blocksKey = ChunkDataKeyFactory.create(x, y, StandardChunkDataType.BLOCKS);
        try {
            // TODO Rewrite Chunk to be a bean and add ChunkCodec
            set(blocksKey, (VanillaBlockDataStorage) chunk.getBlockDataStorage(),
                    new VanillaBlockDataCodec(directPalette, true));
            set(versionKey, StandardWorldDataVersion.MODERN_PALETTE_1_13,
                    new EnumCodec<>(StandardWorldDataVersion.class));
        } catch (IOException e) {
            logger.error("Failed to save corrupted chunk block data at " + x + ":" + y + " in LevelDB '"
                    + worldID + "'", e);
        }
        blocksKey.release();
        // TODO save blockEntities, entities, biome state, version
    }
}
