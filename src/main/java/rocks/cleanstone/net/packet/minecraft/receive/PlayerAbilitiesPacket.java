package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;
import rocks.cleanstone.net.packet.minecraft.enums.PlayerAbilities;

public class PlayerAbilitiesPacket extends ReceivePacket {

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

    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public float getWalkingSpeed() {
        return walkingSpeed;
    }

    @Override
    public PacketType getType() {
        return MinecraftReceivePacketType.PLAYER_ABILITIES;
    }
}
