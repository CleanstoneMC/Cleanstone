package rocks.cleanstone.net.minecraft.packet.inbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbilities;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PlayerAbilitiesPacket implements Packet {

    private final PlayerAbilities[] playerAbilities;
    private final float flyingSpeed;
    private final float walkingSpeed;

    public PlayerAbilitiesPacket(byte playerAbilities, float flyingSpeed, float walkingSpeed) {
        this.playerAbilities = PlayerAbilities.fromBitMask(playerAbilities);
        this.flyingSpeed = flyingSpeed;
        this.walkingSpeed = walkingSpeed;
    }

    public PlayerAbilitiesPacket(PlayerAbilities[] playerAbilities, float flyingSpeed, float walkingSpeed) {
        this.playerAbilities = playerAbilities;
        this.flyingSpeed = flyingSpeed;
        this.walkingSpeed = walkingSpeed;
    }

    public PlayerAbilities[] getPlayerAbilities() {
        return playerAbilities;
    }

    public int getPlayerAbilitiesValue() {
        int value = 0;

        for (PlayerAbilities playerAbility : playerAbilities) {
            value = value & playerAbility.getBit();
        }

        return value;
    }

    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public float getWalkingSpeed() {
        return walkingSpeed;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.PLAYER_ABILITIES;
    }
}
