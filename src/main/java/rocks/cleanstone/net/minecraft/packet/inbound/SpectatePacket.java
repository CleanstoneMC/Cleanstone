package rocks.cleanstone.net.minecraft.packet.inbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

import java.util.UUID;

public class SpectatePacket implements Packet {

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
