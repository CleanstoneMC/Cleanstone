package rocks.cleanstone.game.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Wither;
import rocks.cleanstone.utils.Vector;

@Data
@Getter
public class Rotation implements Serializable {

    private static final long serialVersionUID = -6234637L;

    protected final float yaw, pitch;

    public Rotation(float yaw, float pitch) {
        this.yaw = (yaw % 360 + 360) % 360;
        this.pitch = pitch;
    }

    public Rotation(Rotation rotation) {
        this.yaw = rotation.getYaw();
        this.pitch = rotation.getPitch();
    }

    public float getYaw() {
        return yaw;
    }

    public Rotation withYaw(float yaw) {
        return new Rotation(
                (yaw % 360 + 360) % 360,
                pitch
        );
    }

    public float getPitch() {
        return pitch;
    }

    public Rotation withPitch(float pitch) {
        return new Rotation(
                yaw,
                pitch
        );
    }

    public Vector toUnitVector() {
        final double x = -Math.cos(pitch) * Math.sin(yaw);
        final double y = -Math.sin(pitch);
        final double z = Math.cos(pitch) * Math.cos(yaw);
        return new Vector(x, y, z);
    }
}
