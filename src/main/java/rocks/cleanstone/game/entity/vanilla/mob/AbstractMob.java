package rocks.cleanstone.game.entity.vanilla.mob;

import rocks.cleanstone.game.entity.AbstractEntity;
import rocks.cleanstone.game.entity.EntityType;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.world.World;

public abstract class AbstractMob extends AbstractEntity {

    protected AbstractMob(EntityType type, World world, RotatablePosition position, boolean persistent) {
        super(type, world, position, persistent);
    }
}
