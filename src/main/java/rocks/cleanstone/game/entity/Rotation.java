package rocks.cleanstone.game.entity;

import rocks.cleanstone.utils.Vector;

public class Rotation {
    private float yaw;
    private float pitch;

    public Rotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Rotation(Rotation rotation) {
        this.yaw = rotation.getYaw();
        this.pitch = rotation.getPitch();
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public int getIntYaw() {
        return (int) (yaw % 360 / 360 * 256);
    }

    public int getIntPitch() {
        return (int) (pitch % 360 / 360 * 256);
    }

    public Vector toUnitVector() {
        double x = -Math.cos(pitch) * Math.sin(yaw);
        double y = -Math.sin(pitch);
        double z = Math.cos(pitch) * Math.cos(yaw);
        return new Vector(x, y, z);
    }
}
