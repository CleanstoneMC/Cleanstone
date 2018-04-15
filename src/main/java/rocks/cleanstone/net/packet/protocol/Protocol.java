package rocks.cleanstone.net.packet.protocol;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketTypeRegistry;

public interface Protocol {
    <T extends Packet> PacketCodec<T> getPacketCodec(Class<T> packet, ClientProtocolLayer layer);

    PacketTypeRegistry getPacketTypeRegistry();

    int translateIngoingPacketId(int clientPacketId, ClientProtocolLayer clientLayer);

    int translateOutgoingPacketId(int serverPacketId, ClientProtocolLayer clientLayer);
}
