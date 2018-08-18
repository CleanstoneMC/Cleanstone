package rocks.cleanstone.game.block.state.mapping;

import rocks.cleanstone.game.block.state.BlockState;

public class LegacyBlockStateMapping implements BlockStateMapping<Integer> {

    // TODO allow legacy ID registration

    @Override
    public Integer getID(BlockState state) {
        int metadata = 0; // TODO add property -> legacy metadata mapping
        return state.getBlockType().getID() /* TODO */ << 4 | (metadata & 0xF);
    }

    @Override
    public BlockState getState(Integer id) {
        byte metadata = (byte) (id & 0xF); // TODO add property -> legacy metadata mapping
        int blockID = id >> 4;
        return null; // TODO
    }
}
