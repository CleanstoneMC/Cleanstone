package rocks.cleanstone.net;

import java.net.InetAddress;

import rocks.cleanstone.net.packet.PacketTypeRegistry;
import rocks.cleanstone.net.packet.protocol.Protocol;

public abstract class AbstractNetworking implements Networking {

    protected final int port;

    protected final Protocol protocol;
    private final InetAddress address;
    private final PacketTypeRegistry packetTypeRegistry;

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
    public PacketTypeRegistry getPacketTypeRegistry() {
        return packetTypeRegistry;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public InetAddress getAddress() {
        return address;
    }
}
