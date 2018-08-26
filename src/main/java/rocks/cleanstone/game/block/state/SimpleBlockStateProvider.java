package rocks.cleanstone.game.block.state;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.block.state.property.PropertiesBuilder;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;

import java.lang.reflect.Method;

@CacheConfig(cacheNames={"blockStates"}, keyGenerator = "blockStateCacheKeyGenerator")
@Component("blockStateProvider")
public class SimpleBlockStateProvider implements BlockStateProvider {

    @Cacheable
    @Override
    public BlockState of(BlockType blockType, Properties properties) {
//        System.out.println("two "  + blockType.hashCode());
        return new BlockState(blockType, properties);
    }

    @Cacheable
    @Override
    public BlockState of(BlockType blockType) {
//        System.out.println("one "  + blockType.hashCode());
        return new BlockState(blockType, new Properties(blockType.getProperties()));
    }

    @Cacheable
    @Override
    public <T> BlockState withProperty(BlockType blockType, Property<T> property, T value) {
//        System.out.println("three "  + blockType.hashCode());
        Properties properties = new PropertiesBuilder(blockType).withProperty(property, value).create();

        return new BlockState(blockType, properties);
    }
}

@Component("blockStateCacheKeyGenerator")
class BlockStateCacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object o, Method method, Object... objects) {
        System.out.println("test");
        return o.hashCode();
    }
}