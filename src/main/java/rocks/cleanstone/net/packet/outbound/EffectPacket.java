package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.enums.Effect;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class EffectPacket implements Packet {

    private final Effect effect;
    private final Position location;
    private final int data;
    private final boolean disableRelativeVolume;

    public EffectPacket(int effect, Position location, int data, boolean disableRelativeVolume) {
        this.effect = Effect.fromEffectID(effect);
        this.location = location;
        this.data = data;
        this.disableRelativeVolume = disableRelativeVolume;
    }

    public EffectPacket(Effect effect, Position location, int data, boolean disableRelativeVolume) {
        this.effect = effect;
        this.location = location;
        this.data = data;
        this.disableRelativeVolume = disableRelativeVolume;
    }

    public Effect getEffect() {
        return effect;
    }

    public Position getLocation() {
        return location;
    }

    public int getData() {
        return data;
    }

    public boolean isDisableRelativeVolume() {
        return disableRelativeVolume;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.EFFECT;
    }
}
