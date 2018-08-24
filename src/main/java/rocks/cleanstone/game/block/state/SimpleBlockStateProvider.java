package rocks.cleanstone.game.block.state;

import com.google.common.base.Objects;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.block.state.property.PropertiesBuilder;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;

public class SimpleBlockStateProvider implements BlockStateProvider {

    private static BlockStateProvider INSTANCE;
    private final Cache cache;

    public SimpleBlockStateProvider(CacheManager cacheManager) {
        cache = cacheManager.getCache("blockstates"); //TODO: Use annotation
    }

    public static BlockStateProvider get() {
        return INSTANCE;
    }

    public void init() {
        INSTANCE = this;
    }

    @Override
    public BlockState of(BlockType blockType, Properties properties) {
        return cache.get(Objects.hashCode(blockType, properties), () -> new BlockState(blockType, properties));
    }

    @Override
    public BlockState of(BlockType blockType) {
        return cache.get(blockType.hashCode(), () -> of(blockType, new Properties(blockType.getProperties())));
    }

    @Override
    public <T> BlockState withProperty(BlockType blockType, Property<T> property, T value) {
        return cache.get(Objects.hashCode(blockType, property, value), () -> {
            Properties properties = new PropertiesBuilder(blockType).withProperty(property, value).create();
            return of(blockType, properties);
        });
    }

    public void destroy() {
        INSTANCE = null;
    }
}
