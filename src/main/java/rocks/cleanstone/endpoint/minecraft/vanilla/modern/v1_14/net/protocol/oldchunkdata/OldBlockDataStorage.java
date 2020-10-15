package rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_14.net.protocol.oldchunkdata;

import rocks.cleanstone.game.block.state.BlockState;

public interface OldBlockDataStorage {
    void setBlockState(int x, int y, int z, BlockState state);

    void setBlockLight(int x, int y, int z, byte blockLight);

    void setSkyLight(int x, int y, int z, byte skyLight);
}
