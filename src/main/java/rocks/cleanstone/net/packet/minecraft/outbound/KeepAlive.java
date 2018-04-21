package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

public class KeepAlive {

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.HANDSHAKE;
    }
}
