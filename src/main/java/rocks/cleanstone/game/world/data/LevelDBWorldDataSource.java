package rocks.cleanstone.game.world.data;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.data.Codec;
import rocks.cleanstone.data.VersionedCodec;
import rocks.cleanstone.data.leveldb.LevelDBDataSource;
import rocks.cleanstone.game.entity.EntityTypeRegistry;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.SimpleChunk;
import rocks.cleanstone.game.world.chunk.data.ChunkDataKeyFactory;
import rocks.cleanstone.game.world.chunk.data.StandardChunkDataType;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.DirectPalette;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataCodecFactory;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorage;
import rocks.cleanstone.game.world.chunk.data.entity.EntityData;
import rocks.cleanstone.game.world.chunk.data.entity.EntityDataCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_13.ProtocolBlockStateMapping;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class LevelDBWorldDataSource extends LevelDBDataSource implements WorldDataSource {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory;
    private final String worldID;
    private final boolean hasSkyLight;
    private final DirectPalette directPalette;
    private final EntityTypeRegistry entityTypeRegistry;

    private final Codec<EntityData, ByteBuf> entityDataCodec;
    private final Codec<VanillaBlockDataStorage, ByteBuf> blockDataCodec;

    /**
     * @deprecated Use the {@link WorldDataSourceFactory}
     */
    public LevelDBWorldDataSource(
            VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory,
            EntityTypeRegistry entityTypeRegistry,
            ProtocolBlockStateMapping protocolBlockStateMapping,
            File worldDataFolder,
            String worldID
    ) throws IOException {
        super(new File(worldDataFolder, worldID));
        this.vanillaBlockDataCodecFactory = vanillaBlockDataCodecFactory;
        this.entityTypeRegistry = entityTypeRegistry;
        this.worldID = worldID;

        // TODO read general world data (dimension, seed, etc)
        hasSkyLight = true;
        directPalette = new DirectPalette(protocolBlockStateMapping, 14);

        entityDataCodec = VersionedCodec.withMainCodec(0, new EntityDataCodec(entityTypeRegistry));
        blockDataCodec = VersionedCodec.withMainCodec(0, vanillaBlockDataCodecFactory.get(directPalette, true));
    }

    @Nullable
    @Override
    public Chunk loadExistingChunk(int x, int y) {
        ByteBuf blocksKey = ChunkDataKeyFactory.create(x, y, StandardChunkDataType.BLOCKS);
        ByteBuf entitiesKey = ChunkDataKeyFactory.create(x, y, StandardChunkDataType.ENTITIES);
        VanillaBlockDataStorage blockDataStorage;
        try {
            blockDataStorage = get(blocksKey, blockDataCodec);
            if (blockDataStorage == null) {
                return null;
            }
        } catch (IOException e) {
            logger.error("Failed to load corrupted chunk block data at " + x + ":" + y + " in LevelDB '"
                    + worldID + "'", e);
            return null;
        }
        EntityData entityData;
        try {
            entityData = get(entitiesKey, entityDataCodec);
            if (entityData == null) {
                entityData = new EntityData(new HashSet<>());
            }
        } catch (IOException e) {
            logger.error("Failed to load corrupted chunk entity data at " + x + ":" + y + " in LevelDB '"
                    + worldID + "'", e);
            entityData = new EntityData(new HashSet<>());
        }
        blocksKey.release();
        entitiesKey.release();
        // TODO load blockEntities, biome state
        return new SimpleChunk(blockDataStorage.constructTable(), blockDataStorage, entityData, x, y);
    }

    @Override
    public void saveChunk(Chunk chunk) {
        logger.trace("persisting chunk {}, {}", chunk.getX(), chunk.getZ());
        int x = chunk.getX(), y = chunk.getZ();
        ByteBuf blocksKey = ChunkDataKeyFactory.create(x, y, StandardChunkDataType.BLOCKS);
        ByteBuf entitiesKey = ChunkDataKeyFactory.create(x, y, StandardChunkDataType.ENTITIES);
        try {
            // TODO Rewrite Chunk to be a bean and add ChunkCodec
            set(blocksKey, (VanillaBlockDataStorage) chunk.getBlockDataStorage(), blockDataCodec);
            set(entitiesKey, chunk.getEntityData(), entityDataCodec);
        } catch (IOException e) {
            logger.error("Failed to save corrupted chunk block data at " + x + ":" + y + " in LevelDB '"
                    + worldID + "'", e);
        }
        blocksKey.release();
        entitiesKey.release();
        // TODO save blockEntities, biome state
    }
}
