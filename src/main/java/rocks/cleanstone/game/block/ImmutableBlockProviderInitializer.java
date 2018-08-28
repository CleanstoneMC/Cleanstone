package rocks.cleanstone.game.block;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.CachingBlockStateProvider;

@Component
public class ImmutableBlockProviderInitializer {
    public ImmutableBlockProviderInitializer(CachingImmutableBlockProvider blockStateProvider) {
        ImmutableBlock.setLoadingSource(blockStateProvider);
    }
}
