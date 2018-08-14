package rocks.cleanstone.net.minecraft.protocol.v1_12_2;

import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;

public class ProtocolBlockStateMapping_v1_12_2 implements BlockStateMapping<Integer> {
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
