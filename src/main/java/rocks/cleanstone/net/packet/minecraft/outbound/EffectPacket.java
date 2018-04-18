package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.game.world.region.Position;
import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.Effect;

public class EffectPacket extends OutboundPacket {

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
