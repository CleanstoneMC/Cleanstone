package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.enums.PlayerAbility;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InPlayerAbilitiesPacket implements Packet {

    private final PlayerAbility[] playerAbilities;
    private final float flyingSpeed;
    private final float walkingSpeed;

    public InPlayerAbilitiesPacket(PlayerAbility[] playerAbilities, float flyingSpeed, float walkingSpeed) {
        this.playerAbilities = playerAbilities;
        this.flyingSpeed = flyingSpeed;
        this.walkingSpeed = walkingSpeed;
    }

    public PlayerAbility[] getPlayerAbilities() {
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
        return MinecraftInboundPacketType.PLAYER_ABILITIES;
    }
}
