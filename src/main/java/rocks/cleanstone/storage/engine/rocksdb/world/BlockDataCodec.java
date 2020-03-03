package rocks.cleanstone.storage.engine.rocksdb.world;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.block.state.property.PropertyValuePair;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.storage.chunk.BlockDataStorage;
import rocks.cleanstone.storage.chunk.SimpleBlockDataStorage;
import rocks.cleanstone.storage.world.WorldDataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static rocks.cleanstone.net.utils.ByteBufUtils.readVarInt;
import static rocks.cleanstone.net.utils.ByteBufUtils.writeVarInt;

public class BlockDataCodec implements InOutCodec<BlockDataStorage, ByteBuf> {

    private final boolean hasSkyLight;
    private final WorldDataSource dataSource;

    public BlockDataCodec(WorldDataSource dataSource, boolean hasSkyLight) {
        this.hasSkyLight = hasSkyLight;
        this.dataSource = dataSource;
    }

    @Override
    public BlockDataStorage decode(ByteBuf data) throws IOException {
        BlockDataStorage storage = new SimpleBlockDataStorage(hasSkyLight);
        // TODO: load and save light
        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int z = 0; z < Chunk.WIDTH; z++) {
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    BlockState state = readBlockState(data);
                    storage.setBlock(x, y, z, ImmutableBlock.of(state));
                }
            }
        }
        return storage;
    }

    @Override
    public ByteBuf encode(BlockDataStorage storage) throws IOException {
        ByteBuf data = Unpooled.buffer();
        // TODO: load and save light
        for (int x = 0; x < Chunk.WIDTH; x++) {
            for (int z = 0; z < Chunk.WIDTH; z++) {
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    BlockState state = storage.getBlockState(x, y, z);
                    writeBlockState(data, state);
                }
            }
        }
        return data;
    }

    private void writeBlockState(ByteBuf data, BlockState state) {
        int blockTypeID = dataSource.getBlockTypeID(state.getBlockType());
        writeVarInt(data, blockTypeID);
        writeProperties(data, state.getProperties());
    }

    private BlockState readBlockState(ByteBuf data) throws IOException {
        int blockTypeID = readVarInt(data);
        BlockType blockType = dataSource.getBlockType(blockTypeID);
        Properties properties = readProperties(data, blockType);
        return BlockState.of(blockType, properties);
    }

    private void writeProperties(ByteBuf data, Properties properties) {
        int propertyAmount = properties.getPropertyValuePairs().size();
        writeVarInt(data, propertyAmount);
        properties.getPropertyValuePairs().forEach(pair -> {
            //noinspection rawtypes
            Property property = pair.getProperty();
            writeVarInt(data, dataSource.getPropertyID(property));
            //noinspection unchecked
            writeVarInt(data, property.serialize(pair.getValue()));
        });
    }

    private Properties readProperties(ByteBuf data, BlockType blockType) throws IOException {
        Collection<PropertyValuePair<?>> pairs = new ArrayList<>();
        int propertyAmount = readVarInt(data);
        for (int i = 0; i < propertyAmount; i++) {
            int propertyID = readVarInt(data);
            //noinspection rawtypes
            Property property = dataSource.getProperty(propertyID);
            int serializedProperty = readVarInt(data);
            Object value = property.deserialize(serializedProperty);
            //noinspection unchecked,rawtypes
            pairs.add(new PropertyValuePair(property, value));
        }
        return new Properties(pairs);
    }
}
