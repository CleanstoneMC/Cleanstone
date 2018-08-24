package rocks.cleanstone.game.block.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.block.state.property.PropertiesBuilder;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;

@CacheConfig(cacheNames = {"blockstates"})
public class BlockStateProvider implements BlockStateProviderInterface {

    private static BlockStateProviderInterface INSTANCE;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static BlockStateProviderInterface get() {
        return INSTANCE;
    }

    public void init() {
        INSTANCE = this;
    }

    @Cacheable(cacheNames = "blockstates")
    @Override
    public BlockState of(BlockType blockType, Properties properties) {
        logger.info("Calling of with two Params");
        return new BlockState(blockType, properties);
    }

    @Cacheable(cacheNames = "blockstates")
    @Override
    public BlockState of(BlockType blockType) {
        logger.info("Calling of with one Param " + blockType.hashCode());
        return of(blockType, new Properties(blockType.getProperties()));
    }

    @Cacheable(cacheNames = "blockstates")
    @Override
    public <T> BlockState withProperty(BlockType blockType, Property<T> property, T value) {
        logger.info("Calling of with three Params");
        Properties properties = new PropertiesBuilder(blockType).withProperty(property, value).create();
        return of(blockType, properties);
    }

    public void destroy() {
        INSTANCE = null;
    }
}
