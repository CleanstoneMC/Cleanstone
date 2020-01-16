package rocks.cleanstone.net.protocol;

import rocks.cleanstone.endpoint.minecraft.java.net.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.packet.Packet;

import javax.annotation.Nonnull;
import java.util.Map;

public interface ServerProtocolLayer extends Comparable<ServerProtocolLayer> {
    MinecraftClientProtocolLayer getCorrespondingClientLayer();

    <T extends Packet> void registerPacketCodec(PacketCodec codec, Class<T> packetClass);

    <T extends Packet> InboundPacketCodec<T> getInboundPacketCodec(Class<T> packetClass);

    <T extends Packet> OutboundPacketCodec<T> getOutboundPacketCodec(Class<T> packetClass);

    Map<Class<? extends Packet>, InboundPacketCodec<?>> getInboundPacketClassCodecMap();

    int getOrderedID();

    @Override
    int compareTo(@Nonnull ServerProtocolLayer serverProtocolLayer);
}
