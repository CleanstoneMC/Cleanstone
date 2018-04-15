package rocks.cleanstone.net;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.net.InetAddress;
import java.util.Map;
import java.util.Set;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.protocol.Protocol;

public abstract class AbstractNetworking implements Networking {

    protected final int port;

    protected final Protocol protocol;
    private final InetAddress address;
    private final PacketTypeRegistry packetTypeRegistry;

    private final Map<PacketType, Set<PacketListener>> packetTypeListenersMap = Maps.newConcurrentMap();

    public AbstractNetworking(int port, InetAddress address, PacketTypeRegistry packetTypeRegistry,
                              Protocol protocol) {
        this.port = port;
        this.address = address;
        this.packetTypeRegistry = packetTypeRegistry;
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
            if (packetType.getProtocolType().getProtocolClass() != protocol.getClass())
                throw new IllegalArgumentException(("PacketType to listen for must be of same protocol"));
            packetTypeListenersMap.computeIfAbsent(packetType,
                    key -> Sets.newConcurrentHashSet()).add(packetListener);
        }
    }

    public void callPacketListeners(ReceivePacket packet, Connection connection) {
        packetTypeListenersMap.get(packet.getType()).forEach(
                listener -> listener.onReceive(packet, connection));
    }

    public void callPacketListeners(SendPacket packet, Connection connection) {
        packetTypeListenersMap.get(packet.getType()).forEach(
                listener -> listener.onSend(packet, connection));
    }
}
