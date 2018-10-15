package rocks.cleanstone.game.world.data;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.data.VersionedCodec;
import rocks.cleanstone.data.leveldb.LevelDBDataSource;
import rocks.cleanstone.game.entity.EntityTypeRegistry;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
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
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;

public class LevelDBWorldDataSource extends LevelDBDataSource implements WorldDataSource {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory;
    private final String worldID;
    private final boolean hasSkyLight;
    private final DirectPalette directPalette;
    private final EntityTypeRegistry entityTypeRegistry;

    private final InOutCodec<EntityData, ByteBuf> entityDataCodec;
    private final InOutCodec<VanillaBlockDataStorage, ByteBuf> blockDataCodec;

    /**
     * @deprecated Use the {@link WorldDataSourceFactory}
     */
    public LevelDBWorldDataSource(
            VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory,
            EntityTypeRegistry entityTypeRegistry,
            ProtocolBlockStateMapping protocolBlockStateMapping,
            Path worldDataFolder,
            String worldID
    ) throws IOException {
        super(worldDataFolder.resolve(worldID));
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
    public Chunk loadExistingChunk(ChunkCoords coords) {
        final ByteBuf blocksKey = ChunkDataKeyFactory.create(coords, StandardChunkDataType.BLOCKS);
        final ByteBuf entitiesKey = ChunkDataKeyFactory.create(coords, StandardChunkDataType.ENTITIES);
        final VanillaBlockDataStorage blockDataStorage;
        try {
            blockDataStorage = get(blocksKey, blockDataCodec);
            if (blockDataStorage == null) {
                return null;
            }
        } catch (IOException e) {
            logger.error("Failed to load corrupted chunk block data at " + coords + " in LevelDB '"
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
            logger.error("Failed to load corrupted chunk entity data at " + coords + " in LevelDB '"
                    + worldID + "'", e);
            entityData = new EntityData(new HashSet<>());
        }
        blocksKey.release();
        entitiesKey.release();
        // TODO load blockEntities, biome state
        return new SimpleChunk(blockDataStorage.constructTable(), blockDataStorage, entityData, coords);
    }

    @Override
    public void saveChunk(Chunk chunk) {
        final ChunkCoords coords = chunk.getCoordinates();
        logger.trace("persisting chunk {}, {}", coords);
        final ByteBuf blocksKey = ChunkDataKeyFactory.create(coords, StandardChunkDataType.BLOCKS);
        final ByteBuf entitiesKey = ChunkDataKeyFactory.create(coords, StandardChunkDataType.ENTITIES);
        try {
            // TODO Rewrite Chunk to be a bean and add ChunkCodec
            set(blocksKey, (VanillaBlockDataStorage) chunk.getBlockDataStorage(), blockDataCodec);
            set(entitiesKey, chunk.getEntityData(), entityDataCodec);
        } catch (IOException e) {
            logger.error("Failed to save corrupted chunk block data at " + coords + " in LevelDB '"
                    + worldID + "'", e);
        }
        blocksKey.release();
        entitiesKey.release();
        // TODO save blockEntities, biome state
    }
}
