package rocks.cleanstone.game.entity;

public class Rotation {
    private float pitch;
    private float yaw;

    public Rotation(float pitch, float yaw) {
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Rotation(Rotation rotation) {
        this.pitch = rotation.getPitch();
        this.yaw = rotation.getYaw();
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public int getIntYaw() {
        return (int) (yaw % 360 / 360 * 256);
    }

    public int getIntPitch() {
        return (int) (pitch % 360 / 360 * 256);
    }

}
