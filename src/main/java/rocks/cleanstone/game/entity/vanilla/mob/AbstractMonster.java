package rocks.cleanstone.game.entity.vanilla.mob;

import rocks.cleanstone.game.entity.AbstractLivingEntity;
import rocks.cleanstone.game.entity.EntityType;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.world.World;

public abstract class AbstractMonster extends AbstractLivingEntity {

    public AbstractMonster(EntityType type, World world, HeadRotatablePosition position, boolean persistent) {
        super(type, world, position, persistent);
    }
}
