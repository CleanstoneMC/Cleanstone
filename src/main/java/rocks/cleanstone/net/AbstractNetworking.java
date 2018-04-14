package rocks.cleanstone.net;

import java.net.InetAddress;

import rocks.cleanstone.net.protocol.Protocol;

public abstract class AbstractNetworking implements Networking {

    protected final int port;

    protected final Protocol protocol;
    private final InetAddress address;

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
}
