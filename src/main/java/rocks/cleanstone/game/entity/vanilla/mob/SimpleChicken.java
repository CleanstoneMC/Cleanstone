package rocks.cleanstone.game.entity.vanilla.mob;

import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.SimpleLivingEntity;
import rocks.cleanstone.game.world.World;

public class SimpleChicken extends SimpleLivingEntity implements Chicken {

    // TODO implement custom chicken properties

    public SimpleChicken(World world, HeadRotatablePosition position, boolean persistent,
                         boolean glowing, int health) {
        super(world, position, persistent, glowing, health);
    }
}
