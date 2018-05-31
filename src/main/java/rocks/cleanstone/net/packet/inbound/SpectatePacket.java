package rocks.cleanstone.net.packet.inbound;

import java.util.UUID;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

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
