package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbilities;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PlayerAbilitiesPacket implements Packet {

    private final PlayerAbilities[] playerAbilities;
    private final float flyingSpeed;
    private final float fieldOfViewModifier;

    public PlayerAbilitiesPacket(byte playerAbilities, float flyingSpeed, float fieldOfViewModifier) {
        this.playerAbilities = PlayerAbilities.fromBitMask(playerAbilities);
        this.flyingSpeed = flyingSpeed;
        this.fieldOfViewModifier = fieldOfViewModifier;
    }

    public PlayerAbilitiesPacket(PlayerAbilities[] playerAbilities, float flyingSpeed, float fieldOfViewModifier) {
        this.playerAbilities = playerAbilities;
        this.flyingSpeed = flyingSpeed;
        this.fieldOfViewModifier = fieldOfViewModifier;
    }

    public PlayerAbilities[] getPlayerAbilities() {
        return playerAbilities;
    }

    public byte getPlayerAbilitiesValue() {
        byte value = 0;

        for (PlayerAbilities playerAbility : playerAbilities) {
            value = (byte) (value & playerAbility.getBit());
        }

        return value;
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
