package rocks.cleanstone.net.packet.protocol;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketTypeRegistry;

public interface Protocol {
    PacketCodec getPacketCodec(Class<? extends Packet> packet, ClientProtocolLayer layer);

    PacketTypeRegistry getPacketTypeRegistry();

    int translateInboundPacketID(int clientPacketID, ClientProtocolLayer clientLayer);

    int translateOutboundPacketID(int serverPacketID, ClientProtocolLayer clientLayer);
}
