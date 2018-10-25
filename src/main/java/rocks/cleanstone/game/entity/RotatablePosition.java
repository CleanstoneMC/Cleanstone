package rocks.cleanstone.game.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Wither;
import rocks.cleanstone.game.Position;

@Getter
@EqualsAndHashCode(callSuper = true)
public class RotatablePosition extends Position implements Serializable {
    private static final long serialVersionUID = -290302348L;

    private final Rotation rotation;

    public RotatablePosition(Position position, Rotation rotation) {
        super(position);
        this.rotation = rotation;
    }

    public RotatablePosition(RotatablePosition rotatablePosition) {
        super(rotatablePosition);
        this.rotation = new Rotation(rotatablePosition.getRotation());
    }

    public RotatablePosition withX(double x) {
        return (RotatablePosition) super.withX(x);
    }

    public RotatablePosition withY(double y) {
        return (RotatablePosition) super.withY(y);
    }

    public RotatablePosition withZ(double z) {
        return (RotatablePosition) super.withZ(z);
    }

    public RotatablePosition withRotation(Rotation rotation) {
        return new RotatablePosition(this, rotation);
    }
}
