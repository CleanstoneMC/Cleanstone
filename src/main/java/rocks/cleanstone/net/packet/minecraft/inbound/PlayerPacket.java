package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;

public class PlayerPacket implements Packet {

    private final boolean onGround;

    public PlayerPacket(boolean onGround) {
        this.onGround = onGround;
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.PLAYER;
    }
}
