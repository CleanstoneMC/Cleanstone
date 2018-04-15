package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.InboundPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;

import java.util.UUID;

public class SpectatePacket extends InboundPacket {

    private final UUID targetPlayer;

    public SpectatePacket(UUID targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public UUID getTargetPlayer() {
        return targetPlayer;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.SPECTATE;
    }
}
