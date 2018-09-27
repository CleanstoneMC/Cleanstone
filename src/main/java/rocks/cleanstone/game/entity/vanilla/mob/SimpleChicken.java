package rocks.cleanstone.game.entity.vanilla.mob;

import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.entity.vanilla.VanillaEntityType;
import rocks.cleanstone.game.world.World;

public class SimpleChicken extends AbstractMob {
    protected SimpleChicken(World world, RotatablePosition position, boolean persistent) {
        super(VanillaEntityType.CHICKEN, world, position, persistent);
    }
}
