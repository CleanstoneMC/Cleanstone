package rocks.cleanstone.net;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.net.InetAddress;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.protocol.Protocol;
import rocks.cleanstone.net.utils.SecurityUtils;

public abstract class AbstractNetworking implements Networking {

    protected final int port;

    protected final Protocol protocol;
    private final InetAddress address;
    private final KeyPair keyPair;

    private final Map<PacketType, Set<PacketListener>> packetTypeListenersMap = Maps.newConcurrentMap();

    public AbstractNetworking(int port, InetAddress address, Protocol protocol) {
        this.port = port;
        this.address = address;
        this.protocol = protocol;
        this.keyPair = SecurityUtils.generateKeyPair(1024);
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
    public KeyPair getKeyPair() {
        return keyPair;
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

    @Override
    public Collection<String> getClientAddressBlacklist() {
        return new ArrayList<>();
    }

    public void callInboundPacketListeners(Packet packet, Connection connection) {
        packetTypeListenersMap.get(packet.getType()).forEach(
                listener -> listener.onReceive(packet, connection));
    }

    public void callOutboundPacketListeners(Packet packet, Connection connection) {
        packetTypeListenersMap.get(packet.getType()).forEach(
                listener -> listener.onSend(packet, connection));
    }
}
