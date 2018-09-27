package rocks.cleanstone.net.protocol;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;

public interface Protocol {
    <T extends Packet> OutboundPacketCodec<T> getOutboundPacketCodec(Class<T> packet, ClientProtocolLayer layer);

    <T extends Packet> InboundPacketCodec<T> getInboundPacketCodec(Class<T> packet, ClientProtocolLayer layer);

    PacketTypeRegistry getPacketTypeRegistry();

    PacketType translateInboundPacketID(int clientPacketID, Connection connection);

    int translateOutboundPacketID(PacketType packetType, Connection connection);

    ClientProtocolLayer getDefaultClientLayer();

    ProtocolState getDefaultState();
}
