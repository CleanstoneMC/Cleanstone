package rocks.cleanstone.game.entity;

import com.google.common.base.Objects;
import rocks.cleanstone.utils.Vector;

import java.io.Serializable;

public class Rotation implements Serializable {

    private static final long serialVersionUID = -6234637L;

    protected float yaw, pitch;

    public Rotation(float yaw, float pitch) {
        setYaw(yaw);
        setPitch(pitch);
    }

    public Rotation(Rotation rotation) {
        this.yaw = rotation.getYaw();
        this.pitch = rotation.getPitch();
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = (yaw % 360 + 360) % 360;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public Vector toUnitVector() {
        final double x = -Math.cos(pitch) * Math.sin(yaw);
        final double y = -Math.sin(pitch);
        final double z = Math.cos(pitch) * Math.cos(yaw);
        return new Vector(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rotation)) return false;
        final Rotation rotation = (Rotation) o;
        return Float.compare(rotation.yaw, yaw) == 0 &&
                Float.compare(rotation.pitch, pitch) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(yaw, pitch);
    }

    @Override
    public String toString() {
        return Math.round(yaw) + ":" + Math.round(pitch);
    }
}
