package rocks.cleanstone.net.protocol;

import rocks.cleanstone.game.block.BlockState;

public interface ProtocolBlockStateMapping {
    int map(BlockState state);
}
