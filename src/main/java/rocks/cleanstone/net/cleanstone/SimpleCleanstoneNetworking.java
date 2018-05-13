package rocks.cleanstone.net.cleanstone;

import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.net.cleanstone.protocol.SimpleCleanstoneProtocol;
import rocks.cleanstone.net.netty.NettyNetworking;

import java.net.InetAddress;

public class SimpleCleanstoneNetworking extends NettyNetworking {

    public SimpleCleanstoneNetworking(int port, InetAddress address, SimpleCleanstoneProtocol protocol) {
        super(port, address, protocol);
    }

    public SimpleCleanstoneNetworking(CleanstoneConfig cleanstoneConfig, SimpleCleanstoneProtocol protocol) {
        this(cleanstoneConfig.getPort(), cleanstoneConfig.getAddress(), protocol);
    }
}
