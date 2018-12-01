package rocks.cleanstone.game.entity;

import rocks.cleanstone.game.world.World;

public class SimpleLivingEntity extends SimpleEntity implements LivingEntity {

    protected HeadRotatablePosition position;
    private int health;

    public SimpleLivingEntity(World world, HeadRotatablePosition position, boolean persistent,
                              boolean spawnable, boolean glowing, int health) {
        super(world, position, persistent, spawnable, glowing);
        this.position = position;
        this.health = health;
    }

    @Override
    public HeadRotatablePosition getPosition() {
        return position;
    }

    @Override
    public void setPosition(HeadRotatablePosition position) {
        this.position = position;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }
}
