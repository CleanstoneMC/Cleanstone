package rocks.cleanstone.game.world.region.block.vanilla;

import rocks.cleanstone.game.Material;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.world.region.block.AbstractBlock;
import rocks.cleanstone.game.world.region.block.MiningLevel;

public class SolidBlock extends AbstractBlock {

    protected SolidBlock(Position position, Material material, MiningLevel miningLevel) {
        super(position, material, miningLevel);
    }
}
