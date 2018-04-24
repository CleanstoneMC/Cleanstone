package rocks.cleanstone.net;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.protocol.Protocol;

import java.net.InetAddress;
import java.util.Map;
import java.util.Set;

public abstract class AbstractNetworking implements Networking {

    protected final int port;

    protected final Protocol protocol;
    private final InetAddress address;

    private final Map<PacketType, Set<PacketListener>> packetTypeListenersMap = Maps.newConcurrentMap();

    public AbstractNetworking(int port, InetAddress address, Protocol protocol) {
        this.port = port;
        this.address = address;
        this.protocol = protocol;
    }

    @Override
    public Protocol getProtocol() {
        return protocol;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public InetAddress getAddress() {
        return address;
    }

    @Override
    public void registerPacketListener(PacketListener packetListener, PacketType... packetTypes) {
        for (PacketType packetType : packetTypes) {
            if (!protocol.getPacketTypeRegistry().getPacketTypes().contains(packetType))
                throw new IllegalArgumentException(("PacketType to listen for must be of same protocol"));
            packetTypeListenersMap.computeIfAbsent(packetType,
                    key -> Sets.newConcurrentHashSet()).add(packetListener);
        }
    }

    public void callReceivePacketListeners(Packet packet, Connection connection) {
        packetTypeListenersMap.get(packet.getType()).forEach(
                listener -> listener.onReceive(packet, connection));
    }

    public void callSendPacketListeners(Packet packet, Connection connection) {
        packetTypeListenersMap.get(packet.getType()).forEach(
                listener -> listener.onSend(packet, connection));
    }
}
