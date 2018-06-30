package rocks.cleanstone.game.entity;

public interface LivingEntity extends Entity {
    HeadRotatablePosition getPosition();

    void setPosition(HeadRotatablePosition position);
}
