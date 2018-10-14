package rocks.cleanstone.game.block;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.material.block.BlockType;
import java.util.HashMap;
import java.util.Map;

@Component("immutableBlockProvider")
public class CachingImmutableBlockProvider {
    private final Map<BlockState, ImmutableBlock> blockCache = new HashMap<>();

    public CachingImmutableBlockProvider() {
        ImmutableBlock.setLoadingSource(this);
    }

    // @Cacheable(value = "immutableBlocks", sync = true)
    public ImmutableBlock of(BlockState state) {
        return blockCache.computeIfAbsent(state, ImmutableBlock::new);
    }

    // @Cacheable(value = "immutableBlocks", sync = true)
    public ImmutableBlock of(BlockType blockType) {
        BlockState blockState = BlockState.of(blockType);
        return ImmutableBlock.of(blockState);
    }

    // @Cacheable(value = "immutableBlocks", sync = true)
    public ImmutableBlock of(BlockType blockType, Properties properties) {
        BlockState blockState = BlockState.of(blockType, properties);

        return ImmutableBlock.of(blockState);
    }
}
