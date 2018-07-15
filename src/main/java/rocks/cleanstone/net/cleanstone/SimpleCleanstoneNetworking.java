package rocks.cleanstone.net.cleanstone;

import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;

import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.net.cleanstone.protocol.SimpleCleanstoneProtocol;
import rocks.cleanstone.net.netty.NettyNetworking;
import rocks.cleanstone.player.PlayerManager;

public class SimpleCleanstoneNetworking extends NettyNetworking {

    public SimpleCleanstoneNetworking(int port, InetAddress address, SimpleCleanstoneProtocol protocol,
                                      PlayerManager playerManager) {
        super(port, address, protocol, playerManager);
    }

    @Autowired
    public SimpleCleanstoneNetworking(CleanstoneConfig cleanstoneConfig, SimpleCleanstoneProtocol protocol,
                                      PlayerManager playerManager) {
        this(cleanstoneConfig.getPort(), cleanstoneConfig.getAddress(), protocol, playerManager);
    }
}
