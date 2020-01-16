package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class MobEffectPacket implements Packet {

    private final long runtimeEntityID;
    private final byte eventID;
    private final int effectID;
    private final int amplifier;
    private final boolean particles;
    private final int duration;

    public MobEffectPacket(long runtimeEntityID, byte eventID, int effectID, int amplifier, boolean particles, int duration) {
        this.runtimeEntityID = runtimeEntityID;
        this.eventID = eventID;
        this.effectID = effectID;
        this.amplifier = amplifier;
        this.particles = particles;
        this.duration = duration;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public byte getEventID() {
        return eventID;
    }

    public int getEffectID() {
        return effectID;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public boolean getParticles() {
        return particles;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.MOB_EFFECT;
    }
}

