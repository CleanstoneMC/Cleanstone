package rocks.cleanstone.net.minecraft;

import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.net.minecraft.login.LoginManager;
import rocks.cleanstone.net.minecraft.protocol.SimpleMinecraftProtocol;
import rocks.cleanstone.net.netty.NettyNetworking;

public class SimpleMinecraftNetworking extends NettyNetworking implements MinecraftNetworking {

    @Autowired
    private LoginManager loginManager;

    public SimpleMinecraftNetworking(int port, InetAddress address, SimpleMinecraftProtocol protocol) {
        super(port, address, protocol);
    }

    public SimpleMinecraftNetworking(MinecraftConfig minecraftConfig, SimpleMinecraftProtocol protocol) {
        super(minecraftConfig.getPort(), minecraftConfig.getAddress(), protocol);
    }

    @Override
    public LoginManager getLoginManager() {
        return loginManager;
    }
}
