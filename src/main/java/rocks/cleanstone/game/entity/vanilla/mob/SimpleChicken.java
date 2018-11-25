package rocks.cleanstone.game.entity.vanilla.mob;

import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.vanilla.VanillaEntityType;
import rocks.cleanstone.game.world.World;

public class SimpleChicken extends AbstractMonster {

    public SimpleChicken(World world, HeadRotatablePosition position, boolean persistent) {
        super(VanillaEntityType.CHICKEN, world, position, persistent);
    }
}
