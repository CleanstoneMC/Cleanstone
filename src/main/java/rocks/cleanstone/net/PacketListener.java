package rocks.cleanstone.net;

import rocks.cleanstone.net.packet.InboundPacket;
import rocks.cleanstone.net.packet.OutboundPacket;

public interface PacketListener {
    void onReceive(InboundPacket packet, Connection connection);

    void onSend(OutboundPacket packet, Connection connection);
}
