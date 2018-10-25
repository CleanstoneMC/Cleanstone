package rocks.cleanstone.game.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Wither;
import rocks.cleanstone.game.Position;

@Getter
@EqualsAndHashCode(callSuper = true)
public class HeadRotatablePosition extends RotatablePosition implements Serializable {
    private static final long serialVersionUID = -28351072972L;

    protected final Rotation headRotation;

    public HeadRotatablePosition(Position position, Rotation rotation, Rotation headRotation) {
        super(position, rotation);
        this.headRotation = headRotation;
    }

    public HeadRotatablePosition(RotatablePosition position, Rotation headRotation) {
        super(position, position.getRotation());
        this.headRotation = headRotation;
    }

    public HeadRotatablePosition(RotatablePosition position) {
        super(position);
        this.headRotation = new Rotation(position.getRotation());
    }

    public HeadRotatablePosition(HeadRotatablePosition position) {
        super(position);
        this.headRotation = new Rotation(position.getHeadRotation());
    }

    public HeadRotatablePosition withX(double x) {
        return (HeadRotatablePosition) super.withX(x);
    }

    public HeadRotatablePosition withY(double y) {
        return (HeadRotatablePosition) super.withY(y);
    }

    public HeadRotatablePosition withZ(double z) {
        return (HeadRotatablePosition) super.withZ(z);
    }

    public HeadRotatablePosition withRotation(Rotation rotation) {
        return (HeadRotatablePosition) super.withRotation(rotation);
    }

    public HeadRotatablePosition withHeadRotation(Rotation headRotation) {
        return new HeadRotatablePosition(this, headRotation);
    }
}
