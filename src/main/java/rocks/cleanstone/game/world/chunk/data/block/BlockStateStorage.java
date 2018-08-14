package rocks.cleanstone.game.world.chunk.data.block;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.block.state.BlockState;

/**
 * Stores data about block states that can be converted into a byte stream efficiently
 */
public interface BlockStateStorage {
    void write(ByteBuf out);

    void set(int x, int y, int z, BlockState state);

    BlockState get(int x, int y, int z);
}
