package rocks.cleanstone.net.packet.protocol;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketTypeRegistry;

public interface Protocol {
    <T extends Packet> PacketCodec<T> getPacketCodec(Class<T> packet, ClientProtocolLayer layer);

    PacketTypeRegistry getPacketTypeRegistry();

    int translateInboundPacketId(int clientPacketId, ClientProtocolLayer clientLayer);

    int translateOutboundPacketId(int serverPacketId, ClientProtocolLayer clientLayer);
}
