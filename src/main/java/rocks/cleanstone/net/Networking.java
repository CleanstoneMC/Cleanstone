package rocks.cleanstone.net;

import java.net.InetAddress;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.Protocol;

public interface Networking {

    void start();

    int getPort();

    InetAddress getAddress();

    Protocol getProtocol();

    void sendPacket(Connection connection, Packet packet);

    void registerPacketListener(PacketListener packetListener);
}
