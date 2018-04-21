package rocks.cleanstone.net;

import rocks.cleanstone.net.packet.Packet;

public interface PacketListener {
    void onReceive(Packet packet, Connection connection);

    void onSend(Packet packet, Connection connection);
}
