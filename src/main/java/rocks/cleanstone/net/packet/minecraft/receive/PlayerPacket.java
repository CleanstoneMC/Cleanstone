package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;

public class PlayerPacket extends ReceivePacket {

    private final boolean onGround;

    public PlayerPacket(boolean onGround) {
        this.onGround = onGround;
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public PacketType getType() {
        return MinecraftReceivePacketType.PLAYER;
    }
}
