package rocks.cleanstone.game.entity;

import com.google.common.base.Objects;

import java.io.Serializable;

import rocks.cleanstone.game.Position;

public class HeadRotatablePosition extends RotatablePosition implements Serializable {

    private static final long serialVersionUID = -28351072972L;

    protected Rotation headRotation;

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

    public Rotation getHeadRotation() {
        return headRotation;
    }

    public void setHeadRotation(Rotation headRotation) {
        this.headRotation = headRotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeadRotatablePosition)) return false;
        if (!super.equals(o)) return false;
        HeadRotatablePosition that = (HeadRotatablePosition) o;
        return Objects.equal(headRotation, that.headRotation);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), headRotation);
    }
}
