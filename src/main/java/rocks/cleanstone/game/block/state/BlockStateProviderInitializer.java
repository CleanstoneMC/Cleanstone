package rocks.cleanstone.game.block.state;

import org.springframework.stereotype.Component;

@Component
public class BlockStateProviderInitializer {
    public BlockStateProviderInitializer(CachingBlockStateProvider blockStateProvider) {
        BlockState.setLoadingSource(blockStateProvider);
    }
}
