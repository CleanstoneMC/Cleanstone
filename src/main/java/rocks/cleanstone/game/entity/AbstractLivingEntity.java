package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.world.World;

public abstract class AbstractLivingEntity extends AbstractEntity implements LivingEntity {

    protected HeadRotatablePosition position;

    public AbstractLivingEntity(EntityType type, World world, HeadRotatablePosition position, boolean persistent) {
        super(type, world, position, persistent);
        this.position = position;
    }

    @Override
    public HeadRotatablePosition getPosition() {
        return position;
    }

    @Override
    public void setPosition(HeadRotatablePosition position) {
        this.position = position;
    }
}
