package rocks.cleanstone.game.material.block.vanilla;

import rocks.cleanstone.game.material.Material;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.MiningLevel;

public class SolidBlockType implements BlockType {

    private final MiningLevel miningLevel;
    private final Material material;

    public SolidBlockType(Material material, MiningLevel miningLevel) {
        this.material = material;
        this.miningLevel = miningLevel;
    }

    public MiningLevel getMiningLevel() {
        return miningLevel;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public boolean hasBlockEntity() {
        return false;
    }
}
