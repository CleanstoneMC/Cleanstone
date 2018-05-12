package rocks.cleanstone.game.material.block;

import javax.annotation.Nullable;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.entity.BlockEntity;
import rocks.cleanstone.game.material.Material;

public interface BlockType {

    Material getMaterial();

    @Nullable
    BlockEntity getBlockEntity(Block block);

    boolean hasBlockEntity();
}
