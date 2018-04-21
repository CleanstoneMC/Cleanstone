package rocks.cleanstone.net;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.protocol.Protocol;

import java.net.InetAddress;

public interface Networking {

    int getPort();

    InetAddress getAddress();

    Protocol getProtocol();

    void start();

    void sendPacket(Connection connection, Packet packet);

    void registerPacketListener(PacketListener packetListener, PacketType... packetTypes);
}
