package rocks.cleanstone.net.packet.protocol.minecraft;

import rocks.cleanstone.net.packet.protocol.PacketCodec;

public interface MinecraftPacketCodec extends PacketCodec {
    int getProtocolPacketID();
}
