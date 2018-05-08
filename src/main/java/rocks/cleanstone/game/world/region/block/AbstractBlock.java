package rocks.cleanstone.game.world.region.block;

import rocks.cleanstone.game.Material;
import rocks.cleanstone.game.Position;

public abstract class AbstractBlock implements Block {

    private final Position position;
    private final Material material;
    private final MiningLevel miningLevel;

    protected AbstractBlock(Position position, Material material, MiningLevel miningLevel) {
        this.position = position;
        this.material = material;
        this.miningLevel = miningLevel;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public MiningLevel getMiningLevel() {
        return miningLevel;
    }
}
