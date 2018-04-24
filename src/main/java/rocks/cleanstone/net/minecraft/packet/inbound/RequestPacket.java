package rocks.cleanstone.net.minecraft.packet.inbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;

public class RequestPacket implements Packet {

    public RequestPacket() {
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.REQUEST;
    }
}
