package rocks.cleanstone.net;

import java.net.InetAddress;

import rocks.cleanstone.net.packet.PacketRegistry;
import rocks.cleanstone.net.packet.protocol.Protocol;

public abstract class AbstractNetworking implements Networking {

    protected final int port;

    protected final Protocol protocol;
    private final InetAddress address;
    private final PacketRegistry packetRegistry;

    public AbstractNetworking(int port, InetAddress address, PacketRegistry packetRegistry,
                              Protocol protocol) {
        this.port = port;
        this.address = address;
        this.packetRegistry = packetRegistry;
        this.protocol = protocol;
    }

    @Override
    public Protocol getProtocol() {
        return protocol;
    }

    @Override
    public PacketRegistry getPacketRegistry() {
        return packetRegistry;
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
