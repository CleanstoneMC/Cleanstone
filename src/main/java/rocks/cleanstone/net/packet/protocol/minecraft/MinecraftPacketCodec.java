package rocks.cleanstone.net.packet.protocol.minecraft;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.protocol.PacketCodec;

public interface MinecraftPacketCodec<T extends Packet> extends PacketCodec<T> {
    int getProtocolPacketId();
}
