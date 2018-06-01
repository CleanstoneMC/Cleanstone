package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.enums.Particle;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ParticlePacket implements Packet {

    private final Particle particle;
    private final boolean longDistance;
    private final float x;
    private final float y;
    private final float z;
    private final float offsetX;
    private final float offsetY;
    private final float offsetZ;
    private final float particleData;
    private final int particleCount;
    private final int[] data;

    public ParticlePacket(int particle, boolean longDistance, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float particleData, int particleCount, int[] data) {
        this.particle = Particle.fromParticleID(particle);
        this.longDistance = longDistance;
        this.x = x;
        this.y = y;
        this.z = z;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.particleData = particleData;
        this.particleCount = particleCount;
        this.data = data;
    }

    public ParticlePacket(Particle particle, boolean longDistance, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float particleData, int particleCount, int[] data) {
        this.particle = particle;
        this.longDistance = longDistance;
        this.x = x;
        this.y = y;
        this.z = z;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.particleData = particleData;
        this.particleCount = particleCount;
        this.data = data;
    }

    public Particle getParticle() {
        return particle;
    }

    public boolean isLongDistance() {
        return longDistance;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public float getOffsetZ() {
        return offsetZ;
    }

    public float getParticleData() {
        return particleData;
    }

    public int getParticleCount() {
        return particleCount;
    }

    public int[] getData() {
        return data;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.PARTICLE;
    }
}
