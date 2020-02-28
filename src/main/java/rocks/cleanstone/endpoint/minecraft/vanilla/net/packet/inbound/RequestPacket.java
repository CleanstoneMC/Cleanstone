package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class RequestPacket implements Packet {

    public RequestPacket() {
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.REQUEST;
    }
}
