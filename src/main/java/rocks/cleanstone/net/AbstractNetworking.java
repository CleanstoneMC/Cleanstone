package rocks.cleanstone.net;

import java.net.InetAddress;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Collection;

import rocks.cleanstone.net.protocol.Protocol;
import rocks.cleanstone.net.utils.SecurityUtils;

public abstract class AbstractNetworking implements Networking {

    protected final int port;

    protected final Protocol protocol;
    private final InetAddress address;
    private final KeyPair keyPair;

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
    public Collection<String> getClientAddressBlacklist() {
        return new ArrayList<>();
    }
}
