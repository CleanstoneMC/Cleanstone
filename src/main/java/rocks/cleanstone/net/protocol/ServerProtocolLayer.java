package rocks.cleanstone.net.protocol;

import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.packet.Packet;

import javax.annotation.Nonnull;
import java.util.Map;

public interface ServerProtocolLayer extends Comparable<ServerProtocolLayer> {
    MinecraftClientProtocolLayer getCorrespondingClientLayer();

    <T extends Packet> void registerPacketCodec(PacketCodec codec, Class<T> packetClass);

    PacketCodec getPacketCodec(Class<? extends Packet> packetClass);

    Map<Class<? extends Packet>, PacketCodec> getPacketClassCodecMap();

    int getOrderedID();

    @Override
    int compareTo(@Nonnull ServerProtocolLayer serverProtocolLayer);
}
