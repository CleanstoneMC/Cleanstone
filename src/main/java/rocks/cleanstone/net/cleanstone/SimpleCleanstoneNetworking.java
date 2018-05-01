package rocks.cleanstone.net.cleanstone;

import java.net.InetAddress;

import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.net.cleanstone.protocol.SimpleCleanstoneProtocol;
import rocks.cleanstone.net.netty.NettyNetworking;

public class SimpleCleanstoneNetworking extends NettyNetworking {

    public SimpleCleanstoneNetworking(int port, InetAddress address) {
        super(port, address, new SimpleCleanstoneProtocol());
    }

    public SimpleCleanstoneNetworking(CleanstoneConfig cleanstoneConfig) {
        this(cleanstoneConfig.getPort(), cleanstoneConfig.getAddress());
    }
}
