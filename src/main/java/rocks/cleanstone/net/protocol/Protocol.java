package rocks.cleanstone.net.protocol;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;

public interface Protocol {
    PacketCodec getPacketCodec(Class<? extends Packet> packet, ClientProtocolLayer layer);

    PacketTypeRegistry getPacketTypeRegistry();

    PacketType translateInboundPacketID(int clientPacketID, Connection connection);

    int translateOutboundPacketID(PacketType packetType, Connection connection);

    ClientProtocolLayer getDefaultClientLayer();

    ProtocolState getDefaultState();
}
