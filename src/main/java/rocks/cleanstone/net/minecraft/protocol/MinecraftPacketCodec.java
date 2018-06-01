package rocks.cleanstone.net.minecraft.protocol;

import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.protocol.ProtocolState;

public interface MinecraftPacketCodec extends PacketCodec {
    int getProtocolPacketID();

    ProtocolState getProtocolState();
}
