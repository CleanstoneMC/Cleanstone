package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

public class KeepAlive extends OutboundPacket {

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.HANDSHAKE;
    }
}
