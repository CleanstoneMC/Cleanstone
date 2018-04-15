package rocks.cleanstone.net;

import rocks.cleanstone.net.packet.ReceivePacket;

public interface PacketListener {
    void onReceive(ReceivePacket packet, Connection connection);
}
