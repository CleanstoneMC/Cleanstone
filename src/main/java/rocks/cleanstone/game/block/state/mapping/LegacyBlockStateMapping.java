package rocks.cleanstone.game.block.state.mapping;

import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.utils.SimpleIDMapping;

public class LegacyBlockStateMapping implements BlockStateMapping<Integer> {

    private final SimpleIDMapping<BlockType, Integer> blockTypeMapping;

    public LegacyBlockStateMapping(BlockState defaultState) {
        blockTypeMapping = new SimpleIDMapping<>(defaultState.getBlockType());
    }

    public void setID(BlockType blockType, Integer id) {
        blockTypeMapping.setID(blockType, id);
    }

    @Override
    public Integer getID(BlockState state) {
        int blockTypeID = blockTypeMapping.getID(state.getBlockType());
        byte metadata = (byte) 0;  // TODO add property -> legacy metadata mapping
        return blockTypeID << 4 | (metadata & 0xF);
    }

    @Override
    public BlockState getState(Integer id) {
        byte metadata = (byte) (id & 0xF); // TODO add property -> legacy metadata mapping
        int blockTypeID = id >> 4;
        return BlockState.of(blockTypeMapping.getType(blockTypeID));
    }
}
