package rocks.cleanstone.net.minecraft;

import java.net.InetAddress;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.minecraft.protocol.SimpleMinecraftProtocol;
import rocks.cleanstone.net.netty.NettyNetworking;

public class SimpleMinecraftNetworking extends NettyNetworking {

    public SimpleMinecraftNetworking(MinecraftConfig minecraftConfig, SimpleMinecraftProtocol protocol) {
        this(minecraftConfig.getPort(), minecraftConfig.getAddress(), protocol);
    }

    public SimpleMinecraftNetworking(int port, InetAddress address, SimpleMinecraftProtocol protocol) {
        super(port, address, protocol);
    }
}
