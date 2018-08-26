package rocks.cleanstone.game.block;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.BlockStateProvider;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.material.block.BlockType;

@Component("immutableBlockProvider")
public class SimpleImmutableBlockProvider implements ImmutableBlockProvider {

    private final BlockStateProvider blockStateProvider;

    public SimpleImmutableBlockProvider(BlockStateProvider blockStateProvider) {
        this.blockStateProvider = blockStateProvider;
    }

    @Cacheable("immutableBlocks")
    @Override
    public ImmutableBlock of(BlockState state) {
        //noinspection deprecation
        return new ImmutableBlock(state);
    }

    @Cacheable("immutableBlocks")
    @Override
    public ImmutableBlock of(BlockType blockType) {
        BlockState blockState = blockStateProvider.of(blockType);
        return of(blockState);
    }

    @Cacheable("immutableBlocks")
    @Override
    public ImmutableBlock of(BlockType blockType, Properties properties) {
        BlockState blockState = blockStateProvider.of(blockType, properties);

        return of(blockState);
    }
}
