package rocks.cleanstone.game.world.data;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.data.VersionedCodec;
import rocks.cleanstone.data.leveldb.LevelDBDataSource;
import rocks.cleanstone.game.entity.EntitySerialization;
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
import rocks.cleanstone.net.minecraft.protocol.v1_13_1.ProtocolBlockStateMapping_v1_13_1;

@Slf4j
public class LevelDBWorldDataSource extends LevelDBDataSource implements WorldDataSource {

    private final VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory;
    private final String worldID;
    private final boolean hasSkyLight;
    private final DirectPalette directPalette;

    private final InOutCodec<EntityData, ByteBuf> entityDataCodec;
    private final InOutCodec<VanillaBlockDataStorage, ByteBuf> blockDataCodec;

    /**
     * @deprecated Use the {@link WorldDataSourceFactory}
     */
    public LevelDBWorldDataSource(
            VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory,
            EntitySerialization entitySerialization,
            ProtocolBlockStateMapping_v1_13_1 protocolBlockStateMapping,
            Path worldDataFolder,
            String worldID
    ) throws IOException {
        super(worldDataFolder.resolve(worldID));
        this.vanillaBlockDataCodecFactory = vanillaBlockDataCodecFactory;
        this.worldID = worldID;

        // TODO read general world data (dimension, seed, etc)
        hasSkyLight = true;
        directPalette = new DirectPalette(protocolBlockStateMapping, 14);

        entityDataCodec = VersionedCodec.withMainCodec(0, new EntityDataCodec(entitySerialization));
        blockDataCodec = VersionedCodec.withMainCodec(0, vanillaBlockDataCodecFactory.get(directPalette,
                true, false, false, false, null));
    }

    @Nullable
    @Override
    public Chunk loadExistingChunk(ChunkCoords coords) {
        ByteBuf blocksKey = ChunkDataKeyFactory.create(coords, StandardChunkDataType.BLOCKS);
        ByteBuf entitiesKey = ChunkDataKeyFactory.create(coords, StandardChunkDataType.ENTITIES);
        VanillaBlockDataStorage blockDataStorage;
        try {
            blockDataStorage = get(blocksKey, blockDataCodec);
            if (blockDataStorage == null) {
                return null;
            }
        } catch (IOException e) {
            log.error("Failed to load corrupted chunk block data at " + coords + " in LevelDB '"
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
            log.error("Failed to load corrupted chunk entity data at " + coords + " in LevelDB '"
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
        ChunkCoords coords = chunk.getCoordinates();
        log.trace("persisting chunk {}, {}", coords);
        ByteBuf blocksKey = ChunkDataKeyFactory.create(coords, StandardChunkDataType.BLOCKS);
        ByteBuf entitiesKey = ChunkDataKeyFactory.create(coords, StandardChunkDataType.ENTITIES);
        try {
            // TODO Rewrite Chunk to be a bean and add ChunkCodec
            set(blocksKey, (VanillaBlockDataStorage) chunk.getBlockDataStorage(), blockDataCodec);
            set(entitiesKey, chunk.getEntityData(), entityDataCodec);
        } catch (IOException e) {
            log.error("Failed to save corrupted chunk block data at " + coords + " in LevelDB '"
                    + worldID + "'", e);
        }
        blocksKey.release();
        entitiesKey.release();
        // TODO save blockEntities, biome state
    }
}
