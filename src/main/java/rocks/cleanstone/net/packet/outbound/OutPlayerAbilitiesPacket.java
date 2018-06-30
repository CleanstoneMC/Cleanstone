package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.enums.PlayerAbility;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class OutPlayerAbilitiesPacket implements Packet {

    private final PlayerAbility[] playerAbilities;
    private final float flyingSpeed;
    private final float fieldOfViewModifier;

    public OutPlayerAbilitiesPacket(PlayerAbility[] playerAbilities, float flyingSpeed, float fieldOfViewModifier) {
        this.playerAbilities = playerAbilities;
        this.flyingSpeed = flyingSpeed;
        this.fieldOfViewModifier = fieldOfViewModifier;
    }

    public PlayerAbility[] getPlayerAbilities() {
        return playerAbilities;
    }

    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public float getFieldOfViewModifier() {
        return fieldOfViewModifier;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.PLAYER_ABILITIES;
    }
}
