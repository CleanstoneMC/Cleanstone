package rocks.cleanstone.net;

import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.SendPacket;

public interface PacketListener {
    void onReceive(ReceivePacket packet, Connection connection);

    void onSend(SendPacket packet, Connection connection);
}
