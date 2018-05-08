package rocks.cleanstone.game.world.region.block;

import rocks.cleanstone.game.Material;
import rocks.cleanstone.game.Position;

public interface Block {
    Position getPosition();
    Material getMaterial();
    MiningLevel getMiningLevel();
}
