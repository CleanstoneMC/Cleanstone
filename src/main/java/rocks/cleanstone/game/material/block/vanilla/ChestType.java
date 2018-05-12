package rocks.cleanstone.game.material.block.vanilla;

import javax.annotation.Nullable;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.entity.BlockEntity;
import rocks.cleanstone.game.block.entity.vanilla.Chest;
import rocks.cleanstone.game.material.Material;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.material.block.BlockType;

public class ChestType implements BlockType {
    @Override
    public Material getMaterial() {
        return VanillaMaterial.CHEST;
    }

    @Nullable
    @Override
    public BlockEntity getBlockEntity(Block block) {
        return new Chest(block);
    }

    @Override
    public boolean hasBlockEntity() {
        return true;
    }
}
