package rocks.cleanstone.game.block;

import rocks.cleanstone.game.material.block.BlockType;

public interface Block {

    BlockType getType();

    BlockState getState();
}
