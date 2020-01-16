package rocks.cleanstone.endpoint.minecraft.java.net.chunk;

import com.google.common.base.Objects;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;

public class DirectPalette {

    private final BlockStateMapping<Integer> blockStateMapping;
    /**
     * The maximum amount of bits required to represent a BlockState index
     */
    private final int bitsPerEntry;

    public DirectPalette(BlockStateMapping<Integer> blockStateMapping, int bitsPerEntry) {
        this.blockStateMapping = blockStateMapping;
        this.bitsPerEntry = bitsPerEntry;
    }

    public int getBitsPerEntry() {
        return bitsPerEntry;
    }

    public int getIndex(BlockState state) {
        return blockStateMapping.getID(state);
    }

    public BlockState getBlockState(int index) {
        return blockStateMapping.getState(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DirectPalette)) return false;
        DirectPalette that = (DirectPalette) o;
        return bitsPerEntry == that.bitsPerEntry &&
                Objects.equal(blockStateMapping, that.blockStateMapping);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(blockStateMapping, bitsPerEntry);
    }
}
