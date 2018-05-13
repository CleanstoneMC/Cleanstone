package rocks.cleanstone.game.material.block.vanilla;

import rocks.cleanstone.game.material.Material;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.material.block.BlockType;

public class ChestType implements BlockType {
    @Override
    public Material getMaterial() {
        return VanillaMaterial.CHEST;
    }

    @Override
    public boolean hasBlockEntity() {
        return true;
    }
}
