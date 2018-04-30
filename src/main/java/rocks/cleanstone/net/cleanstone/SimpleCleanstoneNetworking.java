package rocks.cleanstone.net.cleanstone;

import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.cleanstone.protocol.SimpleCleanstoneProtocol;
import rocks.cleanstone.net.minecraft.MinecraftNetworking;
import rocks.cleanstone.net.minecraft.login.LoginManager;
import rocks.cleanstone.net.minecraft.protocol.SimpleMinecraftProtocol;
import rocks.cleanstone.net.netty.NettyNetworking;

import java.net.InetAddress;

public class SimpleCleanstoneNetworking extends NettyNetworking {

    public SimpleCleanstoneNetworking(int port, InetAddress address) {
        super(port, address, new SimpleCleanstoneProtocol());
    }

    public SimpleCleanstoneNetworking(CleanstoneConfig cleanstoneConfig) {
        this(cleanstoneConfig.getPort(), cleanstoneConfig.getAddress());
    }
}
