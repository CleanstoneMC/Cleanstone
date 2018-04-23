package rocks.cleanstone.net;

import rocks.cleanstone.net.packet.Packet;

public abstract class PacketListenerAdapter implements PacketListener {
    @Override
    public void onReceive(Packet packet, Connection connection) {
    }

    @Override
    public void onSend(Packet packet, Connection connection) {
    }
}
