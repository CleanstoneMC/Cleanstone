package rocks.cleanstone.game.entity;

public interface LivingEntity extends Entity {
    Rotation getHeadRotation();

    void setHeadRotation(Rotation headRotation);
}
