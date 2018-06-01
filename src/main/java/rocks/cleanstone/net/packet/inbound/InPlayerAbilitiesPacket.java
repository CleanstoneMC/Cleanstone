package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.enums.PlayerAbilities;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InPlayerAbilitiesPacket implements Packet {

    private final PlayerAbilities[] playerAbilities;
    private final float flyingSpeed;
    private final float walkingSpeed;

    public InPlayerAbilitiesPacket(byte playerAbilities, float flyingSpeed, float walkingSpeed) {
        this.playerAbilities = PlayerAbilities.fromBitMask(playerAbilities);
        this.flyingSpeed = flyingSpeed;
        this.walkingSpeed = walkingSpeed;
    }

    public InPlayerAbilitiesPacket(PlayerAbilities[] playerAbilities, float flyingSpeed, float walkingSpeed) {
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
