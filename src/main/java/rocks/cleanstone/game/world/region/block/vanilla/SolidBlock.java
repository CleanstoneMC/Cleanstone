package rocks.cleanstone.game.world.region.block.vanilla;

import rocks.cleanstone.game.Material;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.world.region.block.AbstractBlock;

public class SolidBlock extends AbstractBlock {

    private final Material material;

    protected SolidBlock(Position position, Material material) {
        super(position);
        this.material = material;
    }

    @Override
    public Material getMaterial() {
        return material;
    }
}
