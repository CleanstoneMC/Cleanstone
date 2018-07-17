package rocks.cleanstone.game.entity;

import com.google.common.base.Objects;

import java.io.Serializable;

import rocks.cleanstone.game.Position;

public class RotatablePosition extends Position implements Serializable {

    private static final long serialVersionUID = -290302348L;

    protected Rotation rotation;

    public RotatablePosition(Position position, Rotation rotation) {
        super(position);
        this.rotation = rotation;
    }

    public RotatablePosition(RotatablePosition rotatablePosition) {
        super(rotatablePosition);
        this.rotation = new Rotation(rotatablePosition.getRotation());
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RotatablePosition)) return false;
        if (!super.equals(o)) return false;
        RotatablePosition that = (RotatablePosition) o;
        return Objects.equal(rotation, that.rotation);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), rotation);
    }

    @Override
    public String toString() {
        return super.toString() + ";Rot=" + rotation;
    }
}
