package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;

import java.util.UUID;

public class SpectatePacket extends ReceivePacket {

    private final UUID targetPlayer;

    public SpectatePacket(UUID targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public UUID getTargetPlayer() {
        return targetPlayer;
    }

    @Override
    public PacketType getType() {
        return MinecraftReceivePacketType.SPECATE;
    }
}
