package rocks.cleanstone.net.cleanstone;

import java.net.InetAddress;

import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.net.cleanstone.protocol.SimpleCleanstoneProtocol;
import rocks.cleanstone.net.netty.NettyNetworking;

public class SimpleCleanstoneNetworking extends NettyNetworking {

    public SimpleCleanstoneNetworking(int port, InetAddress address, SimpleCleanstoneProtocol protocol) {
        super(port, address, protocol);
    }

    public SimpleCleanstoneNetworking(CleanstoneConfig cleanstoneConfig, SimpleCleanstoneProtocol protocol) {
        this(cleanstoneConfig.getPort(), cleanstoneConfig.getAddress(), protocol);
    }
}
