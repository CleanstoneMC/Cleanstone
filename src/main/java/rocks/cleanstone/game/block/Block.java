package rocks.cleanstone.game.block;

import rocks.cleanstone.game.material.Material;
import rocks.cleanstone.game.material.block.BlockType;

public interface Block {

    BlockType getType();

    Material getMaterial();

    byte getMetadata();
}
