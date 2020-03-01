package rocks.cleanstone.storage.engine.leveldb.world;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.data.VersionedCodec;
import rocks.cleanstone.endpoint.minecraft.vanilla.block.VanillaBlockType;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.chunk.ChunkDataEncoder;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.block.state.property.PropertyRegistry;
import rocks.cleanstone.game.entity.EntitySerialization;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.game.world.chunk.SimpleChunk;
import rocks.cleanstone.storage.chunk.BlockDataStorage;
import rocks.cleanstone.storage.chunk.StandardChunkDataType;
import rocks.cleanstone.storage.engine.leveldb.LevelDBDataSource;
import rocks.cleanstone.storage.engine.leveldb.entity.EntityData;
import rocks.cleanstone.storage.engine.leveldb.entity.EntityDataCodec;
import rocks.cleanstone.storage.world.StandardWorldDataType;
import rocks.cleanstone.storage.world.WorldDataKeyFactory;
import rocks.cleanstone.storage.world.WorldDataSource;
import rocks.cleanstone.storage.world.WorldDataSourceFactory;

@Slf4j
public class LevelDBWorldDataSource extends LevelDBDataSource implements WorldDataSource {

    private final String worldID;
    private final boolean hasSkyLight;
    private final BlockTypeIDAssociations blockTypeIDAssociations;
    private final PropertyIDAssociations propertyIDAssociations;

    private final InOutCodec<EntityData, ByteBuf> entityDataCodec;
    private final InOutCodec<BlockDataStorage, ByteBuf> blockDataCodec;
    private final InOutCodec<BlockTypeIDAssociations, ByteBuf> blockTypeIDAssociationsCodec;
    private final InOutCodec<PropertyIDAssociations, ByteBuf> propertyIDAssociationsCodec;


    /**
     * @deprecated Use the {@link WorldDataSourceFactory}
     */
    public LevelDBWorldDataSource(MaterialRegistry materialRegistry,
                                  PropertyRegistry propertyRegistry,
                                  EntitySerialization entitySerialization,
                                  ChunkDataEncoder chunkDataEncoder,
                                  Path worldDataFolder,
                                  String worldID
    ) throws IOException {
        super(worldDataFolder.resolve(worldID));
        this.worldID = worldID;
        hasSkyLight = true;

        entityDataCodec = VersionedCodec.withMainCodec(0, new EntityDataCodec(entitySerialization));
        blockDataCodec = VersionedCodec.withMainCodec(0, new BlockDataCodec(this, hasSkyLight));
        blockTypeIDAssociationsCodec = VersionedCodec.withMainCodec(0,
                new BlockTypeIDAssociationsCodec(worldID, materialRegistry));
        propertyIDAssociationsCodec = VersionedCodec.withMainCodec(0,
                new PropertyIDAssociationsCodec(worldID, propertyRegistry));

        // TODO read general world data (dimension, seed, etc)
        blockTypeIDAssociations = loadBlockTypeIDAssociations();
        propertyIDAssociations = loadPropertyIDAssociations();
    }

    @Nullable
    @Override
    public Chunk loadExistingChunk(ChunkCoords coords) {
        ByteBuf blocksKey = WorldDataKeyFactory.createForChunk(coords, StandardChunkDataType.BLOCKS);
        ByteBuf entitiesKey = WorldDataKeyFactory.createForChunk(coords, StandardChunkDataType.ENTITIES);
        BlockDataStorage blockDataStorage;
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
        return new SimpleChunk(blockDataStorage, entityData, coords);
    }

    @Override
    public void saveChunk(Chunk chunk) {
        ChunkCoords coords = chunk.getCoordinates();
        log.trace("persisting chunk {}", coords);
        ByteBuf blocksKey = WorldDataKeyFactory.createForChunk(coords, StandardChunkDataType.BLOCKS);
        ByteBuf entitiesKey = WorldDataKeyFactory.createForChunk(coords, StandardChunkDataType.ENTITIES);
        try {
            // TODO Rewrite Chunk to be a bean and add ChunkCodec
            set(blocksKey, chunk.getBlockDataStorage(), blockDataCodec);
            set(entitiesKey, chunk.getEntityData(), entityDataCodec);
        } catch (IOException e) {
            log.error("Failed to save chunk block data at " + coords + " in LevelDB '" + worldID + "'", e);
        }
        blocksKey.release();
        entitiesKey.release();
        // TODO save blockEntities, biome state
    }

    @Override
    public synchronized int getBlockTypeID(BlockType blockType) {
        return blockTypeIDAssociations.getBlockTypeIDMap().computeIfAbsent(blockType, (type) -> {
            int numericID = blockTypeIDAssociations.getLargestAssignedNumericID() + 1;
            blockTypeIDAssociations.getBlockTypeIDMap().put(blockType, numericID);
            log.debug("Added numeric id " + numericID + " to '" + worldID + "'");
            saveBlockTypeIDAssociations(blockTypeIDAssociations);
            return numericID;
        });
    }

    @Override
    public synchronized BlockType getBlockType(int numericID) {
        return blockTypeIDAssociations.getBlockTypeIDMap().inverse().computeIfAbsent(numericID, (id) -> {
            log.error("Cannot find blockType for numericID " + numericID
                    + " in blockTypeIDAssociations for '" + worldID + "', replacing with AIR!");
            return VanillaBlockType.AIR;
        });
    }

    private BlockTypeIDAssociations loadBlockTypeIDAssociations() {
        ByteBuf blockTypesKey = WorldDataKeyFactory.create(StandardWorldDataType.BLOCK_TYPE_IDS);
        try {
            return get(blockTypesKey, blockTypeIDAssociationsCodec);
        } catch (IOException e) {
            log.error("Failed to load corrupted blockTypeIDAssociations data in LevelDB '" + worldID + "'", e);
        }
        return new BlockTypeIDAssociations();
    }

    private void saveBlockTypeIDAssociations(BlockTypeIDAssociations blockTypeIDAssociations) {
        ByteBuf blockTypesKey = WorldDataKeyFactory.create(StandardWorldDataType.BLOCK_TYPE_IDS);
        try {
            set(blockTypesKey, blockTypeIDAssociations, blockTypeIDAssociationsCodec);
        } catch (IOException e) {
            log.error("Failed to save blockTypeIDAssociations data in LevelDB '" + worldID + "'", e);
        }
    }

    @Override
    public synchronized int getPropertyID(Property<?> property) {
        return propertyIDAssociations.getPropertyIDMap().computeIfAbsent(property, (type) -> {
            int numericID = propertyIDAssociations.getLargestAssignedNumericID() + 1;
            propertyIDAssociations.getPropertyIDMap().put(property, numericID);
            log.debug("Added numeric id " + numericID + " to '" + worldID + "'");
            savePropertyIDAssociations(propertyIDAssociations);
            return numericID;
        });
    }

    @Override
    public synchronized Property<?> getProperty(int numericID) throws IllegalArgumentException {
        return propertyIDAssociations.getPropertyIDMap().inverse().computeIfAbsent(numericID, (id) -> {
            throw new IllegalArgumentException("Cannot find property for numericID " + numericID
                    + " in propertyIDAssociations for '" + worldID + "'!");
        });
    }

    private PropertyIDAssociations loadPropertyIDAssociations() {
        ByteBuf propertiesKey = WorldDataKeyFactory.create(StandardWorldDataType.PROPERTY_IDS);
        try {
            return get(propertiesKey, propertyIDAssociationsCodec);
        } catch (IOException e) {
            log.error("Failed to load corrupted propertyIDAssociations data in LevelDB '" + worldID + "'", e);
        }
        return new PropertyIDAssociations();
    }

    private void savePropertyIDAssociations(PropertyIDAssociations propertyIDAssociations) {
        ByteBuf propertiesKey = WorldDataKeyFactory.create(StandardWorldDataType.PROPERTY_IDS);
        try {
            set(propertiesKey, propertyIDAssociations, propertyIDAssociationsCodec);
        } catch (IOException e) {
            log.error("Failed to save propertyIDAssociations data in LevelDB '" + worldID + "'", e);
        }
    }
}
